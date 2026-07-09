package com.marinboy.marinboy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.marinboy.marinboy.auth.AuthRole;
import com.marinboy.marinboy.auth.AuthSession;
import com.marinboy.marinboy.auth.SessionUser;
import com.marinboy.marinboy.dto.CreateReservationRequest;
import com.marinboy.marinboy.dto.HolidayRequest;
import com.marinboy.marinboy.dto.SalonReservationResponse;
import com.marinboy.marinboy.model.ReservationStatus;
import com.marinboy.marinboy.service.DatabaseVerificationService;
import com.marinboy.marinboy.service.SalonReservationService;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

// 미용실 예약관리의 주요 흐름을 검증하는 통합 테스트입니다.
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MarinboyApplicationTests {

    private static final String CONFLICT_MESSAGE = "이미 예약이 있는 시간대";
    private static final String BUSINESS_HOURS_MESSAGE = "영업시간은 10:00부터 20:00까지";
    private static final String SUNDAY_MESSAGE = "일요일은 예약을 받지 않습니다";
    private static final String THIRTY_MINUTE_MESSAGE = "예약은 30분 단위";
    private static final String HOLIDAY_MESSAGE = "선택한 날짜는 휴무일";
    private static final String NO_SHOW_BLOCK_MESSAGE = "노쇼 이력이 있는 연락처";

    @Autowired
    private SalonReservationService salonReservationService;

    @Autowired
    private DatabaseVerificationService databaseVerificationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        // 애플리케이션 시작 후 샘플 데이터가 로드되는지 확인합니다.
        assertThat(salonReservationService.getServiceCatalog()).isNotEmpty();
        assertThat(salonReservationService.getServiceCatalog().get(0).getImageUrl()).startsWith("https://");
        assertThat(salonReservationService.getReservations()).isNotEmpty();
    }

    @Test
    void customerPageDoesNotExposeAdminFeatures() throws Exception {
        // 고객 화면에는 예약 요청 기능만 보이고 관리자 기능은 숨겨져야 합니다.
        mockMvc.perform(get("/").session(customerSession()))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("예약 요청")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("카테고리 선택")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("이전 시술 이력")))
                .andExpect(content().string(org.hamcrest.Matchers.not(org.hamcrest.Matchers.containsString("시술 메뉴 관리"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(org.hamcrest.Matchers.containsString("휴무일 관리"))));
    }

    @Test
    void adminPageExposesAdminFeatures() throws Exception {
        // 관리자 화면에는 예약 관리, 가격 수정, 휴무일 관리 기능이 보여야 합니다.
        mockMvc.perform(get("/admin").session(adminSession()))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("예약 관리")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("시술 메뉴 관리")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("휴무일 관리")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("노쇼 방지 알림 대상")));
    }

    @Test
    void pageRequiresLoginSession() throws Exception {
        // 로그인하지 않은 사용자는 고객 화면에 바로 접근할 수 없습니다.
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void customerCannotOpenAdminPage() throws Exception {
        // 고객 권한 세션은 관리자 화면에 접근할 수 없습니다.
        mockMvc.perform(get("/admin").session(customerSession()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void conflictingReservationIsRejected() {
        // 시간 단위 검증에 방해받지 않도록 정상 예약을 먼저 만든 뒤 중복 규칙만 검증합니다.
        Long serviceId = salonReservationService.getServiceCatalog().stream()
                .filter(serviceItem -> serviceItem.getDurationMinutes() == 60)
                .findFirst()
                .orElseThrow()
                .getId();
        LocalDateTime existingTime = nextOpenDay().with(LocalTime.of(10, 0));

        salonReservationService.createReservation(new CreateReservationRequest(
                serviceId,
                "기준 고객",
                "base@example.com",
                "010-9999-0000",
                existingTime,
                true,
                "기준 예약"));

        CreateReservationRequest request = new CreateReservationRequest(
                serviceId,
                "테스트 고객",
                "tester@example.com",
                "010-0000-1111",
                existingTime.plusMinutes(30),
                true,
                "중복 예약 검증");

        assertThatThrownBy(() -> salonReservationService.createReservation(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(CONFLICT_MESSAGE);
    }

    @Test
    void reservationStatusCanBeUpdated() {
        // 원장이 예약 상태를 변경할 수 있는지 확인합니다.
        Long reservationId = salonReservationService.getReservations().get(0).id();
        assertThat(salonReservationService.updateStatus(reservationId, ReservationStatus.COMPLETED).status())
                .isEqualTo(ReservationStatus.COMPLETED);
    }

    @Test
    void noShowStatusBlocksSamePhoneReservation() {
        // 노쇼 처리된 연락처는 새 예약 요청을 바로 등록할 수 없는지 확인합니다.
        Long serviceId = salonReservationService.getServiceCatalog().get(0).getId();
        LocalDateTime noShowTime = nextOpenDay().plusDays(5).with(LocalTime.of(10, 0));
        CreateReservationRequest noShowRequest = new CreateReservationRequest(
                serviceId,
                "노쇼 고객",
                "noshow@example.com",
                "010-4444-5555",
                noShowTime,
                true,
                "노쇼 처리 대상");

        Long reservationId = salonReservationService.createReservation(noShowRequest).id();
        salonReservationService.updateStatus(reservationId, ReservationStatus.CONFIRMED);
        salonReservationService.updateStatus(reservationId, ReservationStatus.NO_SHOW);

        CreateReservationRequest blockedRequest = new CreateReservationRequest(
                serviceId,
                "노쇼 고객 재예약",
                "noshow2@example.com",
                "010-4444-5555",
                noShowTime.plusDays(1),
                true,
                "재예약 시도");

        assertThatThrownBy(() -> salonReservationService.createReservation(blockedRequest))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(NO_SHOW_BLOCK_MESSAGE);
    }

    @Test
    void reminderTargetsIncludeUpcomingRequestedReservation() {
        // 가까운 예약은 관리자 알림 대상 목록에 포함되는지 확인합니다.
        Long serviceId = salonReservationService.getServiceCatalog().get(0).getId();
        LocalDateTime reminderTime = nextReminderDay().with(LocalTime.of(10, 0));
        SalonReservationResponse response = salonReservationService.createReservation(new CreateReservationRequest(
                serviceId,
                "알림 대상 고객",
                "reminder@example.com",
                "010-7777-1111",
                reminderTime,
                true,
                "알림 대상 검증"));

        assertThat(salonReservationService.getReminderTargets())
                .anySatisfy(target -> assertThat(target.reservationId()).isEqualTo(response.id()));
    }

    @Test
    void databaseTimeQueryWorksInDefaultProfile() {
        // 현재 활성 데이터소스가 설정된 시간 조회 쿼리를 실행할 수 있는지 확인합니다.
        assertThat(databaseVerificationService.getDatabaseTime().vendor()).isEqualTo("H2");
        assertThat(databaseVerificationService.getDatabaseTime().databaseTime()).isNotBlank();
    }

    @Test
    void reservationOutsideBusinessHoursIsRejected() {
        // 예약 시간이 미용실 영업시간 안에 있어야 하는지 확인합니다.
        Long serviceId = salonReservationService.getServiceCatalog().get(0).getId();
        LocalDateTime invalidTime = nextOpenDay().with(LocalTime.of(9, 0));

        CreateReservationRequest request = new CreateReservationRequest(
                serviceId,
                "이른 예약 고객",
                "early@example.com",
                "010-1111-2222",
                invalidTime,
                true,
                "영업 시작 전 예약");

        assertThatThrownBy(() -> salonReservationService.createReservation(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(BUSINESS_HOURS_MESSAGE);
    }

    @Test
    void sundayReservationIsRejected() {
        // 정기 휴무일인 일요일에는 예약할 수 없는지 확인합니다.
        Long serviceId = salonReservationService.getServiceCatalog().get(0).getId();
        LocalDateTime sundayTime = nextSunday().with(LocalTime.of(10, 0));

        CreateReservationRequest request = new CreateReservationRequest(
                serviceId,
                "일요일 예약 고객",
                "sunday@example.com",
                "010-3333-4444",
                sundayTime,
                true,
                "휴무일 예약");

        assertThatThrownBy(() -> salonReservationService.createReservation(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(SUNDAY_MESSAGE);
    }

    @Test
    void nonThirtyMinuteSlotIsRejected() {
        // 예약 시작 시간이 30분 단위를 따라야 하는지 확인합니다.
        Long serviceId = salonReservationService.getServiceCatalog().get(0).getId();
        LocalDateTime invalidSlot = nextOpenDay().with(LocalTime.of(10, 15));

        CreateReservationRequest request = new CreateReservationRequest(
                serviceId,
                "잘못된 시간 고객",
                "slot@example.com",
                "010-5555-6666",
                invalidSlot,
                true,
                "잘못된 예약 단위");

        assertThatThrownBy(() -> salonReservationService.createReservation(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(THIRTY_MINUTE_MESSAGE);
    }

    @Test
    void availableSlotsExcludeConflictingTimes() {
        // 예약 가능 시간 조회가 이미 막힌 시간대를 숨기는지 확인합니다.
        Long serviceId = salonReservationService.getServiceCatalog().stream()
                .filter(serviceItem -> serviceItem.getDurationMinutes() == 60)
                .findFirst()
                .orElseThrow()
                .getId();
        LocalDate date = nextOpenDay().toLocalDate();
        LocalDateTime blockedTime = date.atTime(10, 0);

        salonReservationService.createReservation(new CreateReservationRequest(
                serviceId,
                "차단 시간 고객",
                "blocked@example.com",
                "010-7777-8888",
                blockedTime,
                true,
                "두 시간대를 차단하는 예약"));

        assertThat(salonReservationService.getAvailableSlots(serviceId, date).availableSlots())
                .doesNotContain(blockedTime, blockedTime.plusMinutes(30))
                .contains(date.atTime(11, 0));
    }

    @Test
    void servicePriceCanBeUpdatedByApi() throws Exception {
        // 원장이 관리자 화면에서 시술 가격을 변경할 수 있는지 확인합니다.
        String requestBody = """
                {
                  "price": 42000
                }
                """;

        mockMvc.perform(patch("/api/services/1/price")
                        .session(adminSession())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.price").value(42000));
    }

    @Test
    void topServicesCanBeLoadedAfterLogin() throws Exception {
        // 고객과 관리자가 로그인 후 이달의 헤어 TOP3를 확인할 수 있는지 검증합니다.
        mockMvc.perform(get("/api/services/top3")
                        .session(customerSession()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].topRank").value(1))
                .andExpect(jsonPath("$[1].topRank").value(2))
                .andExpect(jsonPath("$[2].topRank").value(3));
    }

    @Test
    void adminCanUploadServiceImageFile() throws Exception {
        // 관리자가 URL 대신 이미지 파일을 업로드해 시술 메뉴 대표 이미지로 사용할 수 있는지 확인합니다.
        MockMultipartFile image = new MockMultipartFile(
                "file",
                "service-image.png",
                "image/png",
                new byte[] {1, 2, 3, 4});

        mockMvc.perform(multipart("/api/service-images")
                        .file(image)
                        .session(adminSession()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imageUrl").value(org.hamcrest.Matchers.startsWith("/uploads/service-images/")));
    }

    @Test
    void serviceMenuCanBeCreatedUpdatedAndDeletedByAdmin() throws Exception {
        // 원장이 새 시술 메뉴를 등록, 수정, 삭제할 수 있는지 확인합니다.
        String createBody = """
                {
                  "name": "앞머리 컷",
                  "category": "컷",
                  "durationMinutes": 20,
                  "price": 12000,
                  "description": "앞머리 라인을 빠르게 정리하는 시술입니다.",
                  "imageUrl": "https://example.com/bang-cut.jpg",
                  "additionalImageUrls": [
                    "https://example.com/bang-cut-detail-1.jpg",
                    "https://example.com/bang-cut-detail-2.jpg"
                  ],
                  "topRank": 1
                }
                """;

        String createdJson = mockMvc.perform(post("/api/services")
                        .session(adminSession())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("앞머리 컷"))
                .andExpect(jsonPath("$.imageUrl").value("https://example.com/bang-cut.jpg"))
                .andExpect(jsonPath("$.additionalImageUrls.length()").value(2))
                .andExpect(jsonPath("$.topRank").value(1))
                .andReturn()
                .getResponse()
                .getContentAsString();
        Number serviceId = com.jayway.jsonpath.JsonPath.read(createdJson, "$.id");

        String updateBody = """
                {
                  "name": "앞머리 디자인 컷",
                  "category": "컷",
                  "durationMinutes": 30,
                  "price": 15000,
                  "description": "앞머리와 얼굴 라인을 함께 정리하는 시술입니다.",
                  "imageUrl": "https://example.com/design-bang-cut.jpg",
                  "additionalImageUrls": [],
                  "topRank": null
                }
                """;

        mockMvc.perform(put("/api/services/" + serviceId.longValue())
                        .session(adminSession())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("앞머리 디자인 컷"))
                .andExpect(jsonPath("$.durationMinutes").value(30))
                .andExpect(jsonPath("$.imageUrl").value("https://example.com/design-bang-cut.jpg"))
                .andExpect(jsonPath("$.additionalImageUrls.length()").value(0))
                .andExpect(jsonPath("$.topRank").value(org.hamcrest.Matchers.nullValue()));

        mockMvc.perform(delete("/api/services/" + serviceId.longValue())
                        .session(adminSession()))
                .andExpect(status().isNoContent());
    }

    @Test
    void customerHistoryCanBeLoadedByPhone() throws Exception {
        // 같은 연락처 고객의 이전 시술 이력을 고객 화면에서 조회할 수 있는지 확인합니다.
        mockMvc.perform(get("/api/customers/history")
                        .session(customerSession())
                        .param("customerPhone", "010-1234-5678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].serviceName").value("시그니처 컷"))
                .andExpect(jsonPath("$[0].status").value("CONFIRMED"));
    }

    @Test
    void registeredHolidayBlocksSlotsAndReservationCreation() {
        // 원장이 지정한 특정 휴무일에는 예약 가능 시간이 사라지고 예약 생성도 막히는지 확인합니다.
        Long serviceId = salonReservationService.getServiceCatalog().get(0).getId();
        LocalDate holidayDate = nextOpenDay().plusDays(4).toLocalDate();
        while (holidayDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            holidayDate = holidayDate.plusDays(1);
        }

        salonReservationService.saveHoliday(new HolidayRequest(holidayDate, "원장 교육 참석"));

        assertThat(salonReservationService.getAvailableSlots(serviceId, holidayDate).availableSlots())
                .isEmpty();

        CreateReservationRequest request = new CreateReservationRequest(
                serviceId,
                "휴무일 예약 고객",
                "holiday@example.com",
                "010-2222-4444",
                holidayDate.atTime(10, 0),
                true,
                "휴무일 예약 시도");

        assertThatThrownBy(() -> salonReservationService.createReservation(request))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(HOLIDAY_MESSAGE);
    }

    @Test
    void holidayApiRegistersHoliday() throws Exception {
        // 휴무일 등록 API가 JSON 응답으로 등록된 날짜와 사유를 반환하는지 확인합니다.
        LocalDate holidayDate = nextOpenDay().plusDays(7).toLocalDate();
        while (holidayDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            holidayDate = holidayDate.plusDays(1);
        }
        String requestBody = """
                {
                  "holidayDate": "%s",
                  "reason": "개인 일정"
                }
                """.formatted(holidayDate);

        mockMvc.perform(post("/api/holidays")
                        .session(adminSession())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.holidayDate").value(holidayDate.toString()))
                .andExpect(jsonPath("$.reason").value("개인 일정"));
    }

    @Test
    void conflictingReservationApiReturnsStructuredConflictResponse() throws Exception {
        // 이미 예약된 시간대 요청 시 프론트가 읽을 수 있는 JSON 에러를 받는지 확인합니다.
        String nextDate = nextOpenDay().toLocalDate().toString();
        String requestBody = """
                {
                  "serviceId": 1,
                  "customerName": "기준 고객",
                  "customerEmail": "base@example.com",
                  "customerPhone": "010-9999-0000",
                  "reservationDateTime": "%sT10:00:00",
                  "noShowPolicyAgreed": true,
                  "memo": "기준 예약"
                }
                """.formatted(nextDate);
        String conflictBody = """
                {
                  "serviceId": 1,
                  "customerName": "중복 예약 고객",
                  "customerEmail": "conflict@example.com",
                  "customerPhone": "010-2222-3333",
                  "reservationDateTime": "%sT10:30:00",
                  "noShowPolicyAgreed": true,
                  "memo": "중복 예약 요청"
                }
                """.formatted(nextDate);

        mockMvc.perform(post("/api/reservations")
                        .session(customerSession())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/reservations")
                        .session(customerSession())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(conflictBody))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.error").value("Conflict"))
                .andExpect(jsonPath("$.message").value("이미 예약이 있는 시간대입니다."))
                .andExpect(jsonPath("$.path").value("/api/reservations"));
    }

    @Test
    void invalidReservationApiReturnsStructuredValidationResponse() throws Exception {
        // Bean Validation 실패가 공통 JSON 응답으로 프론트에 전달되는지 확인합니다.
        String requestBody = """
                {
                  "serviceId": 1,
                  "customerName": "",
                  "customerEmail": "not-an-email",
                  "customerPhone": "",
                  "reservationDateTime": null,
                  "noShowPolicyAgreed": false,
                  "memo": "잘못된 예약 요청"
                }
                """;

        mockMvc.perform(post("/api/reservations")
                        .session(customerSession())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.path").value("/api/reservations"));
    }

    @Test
    void reservationApiRequiresNoShowPolicyAgreement() throws Exception {
        // 고객이 노쇼 방지 안내에 동의하지 않으면 예약 요청을 거절하는지 확인합니다.
        String nextDate = nextOpenDay().toLocalDate().toString();
        String requestBody = """
                {
                  "serviceId": 1,
                  "customerName": "동의 거부 고객",
                  "customerEmail": "policy@example.com",
                  "customerPhone": "010-2222-9999",
                  "reservationDateTime": "%sT10:00:00",
                  "noShowPolicyAgreed": false,
                  "memo": "동의 거부 테스트"
                }
                """.formatted(nextDate);

        mockMvc.perform(post("/api/reservations")
                        .session(customerSession())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("noShowPolicyAgreed: 노쇼 방지 예약 안내에 동의해야 합니다."));
    }

    @Test
    void invalidAvailableSlotsDateReturnsStructuredBadRequestResponse() throws Exception {
        // 잘못된 쿼리 파라미터도 같은 에러 응답 구조를 사용하는지 확인합니다.
        mockMvc.perform(get("/api/services/1/available-slots")
                        .session(customerSession())
                        .param("date", "not-a-date"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("date: 올바르지 않은 요청값입니다."))
                .andExpect(jsonPath("$.path").value("/api/services/1/available-slots"));
    }

    private LocalDateTime nextOpenDay() {
        // 검증 테스트에서 안전하게 사용할 다음 일요일이 아닌 날짜를 찾습니다.
        LocalDateTime base = LocalDateTime.now().plusDays(2).withSecond(0).withNano(0);
        while (base.getDayOfWeek() == DayOfWeek.SUNDAY) {
            base = base.plusDays(1);
        }
        return base;
    }

    private LocalDateTime nextSunday() {
        // 휴무일 규칙을 직접 테스트하기 위해 다음 일요일 날짜를 찾습니다.
        LocalDateTime base = LocalDateTime.now().plusDays(1).withSecond(0).withNano(0);
        while (base.getDayOfWeek() != DayOfWeek.SUNDAY) {
            base = base.plusDays(1);
        }
        return base;
    }

    private LocalDateTime nextReminderDay() {
        // 알림 대상 테스트에서 사용할 2일 이내의 영업일을 찾습니다.
        LocalDateTime base = LocalDateTime.now().plusDays(1).withSecond(0).withNano(0);
        while (base.getDayOfWeek() == DayOfWeek.SUNDAY) {
            base = base.plusDays(1);
        }
        return base;
    }

    private MockHttpSession customerSession() {
        // 고객 권한이 필요한 화면과 API 테스트에 사용할 세션입니다.
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(AuthSession.LOGIN_USER, new SessionUser("customer", "고객 사용자", AuthRole.CUSTOMER));
        return session;
    }

    private MockHttpSession adminSession() {
        // 관리자 권한이 필요한 화면과 API 테스트에 사용할 세션입니다.
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(AuthSession.LOGIN_USER, new SessionUser("admin", "원장 관리자", AuthRole.ADMIN));
        return session;
    }
}
