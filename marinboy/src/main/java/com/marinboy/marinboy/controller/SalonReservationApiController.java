package com.marinboy.marinboy.controller;

import com.marinboy.marinboy.auth.AuthSession;
import com.marinboy.marinboy.auth.SessionUser;
import com.marinboy.marinboy.dto.AvailableSlotResponse;
import com.marinboy.marinboy.dto.CreateReservationRequest;
import com.marinboy.marinboy.dto.CustomerHistoryResponse;
import com.marinboy.marinboy.dto.DatabaseTimeResponse;
import com.marinboy.marinboy.dto.HolidayRequest;
import com.marinboy.marinboy.dto.HolidayResponse;
import com.marinboy.marinboy.dto.ImageUploadResponse;
import com.marinboy.marinboy.dto.NoShowAlertResponse;
import com.marinboy.marinboy.dto.ReservationStatusUpdateRequest;
import com.marinboy.marinboy.dto.SalonReservationResponse;
import com.marinboy.marinboy.dto.SalonServiceResponse;
import com.marinboy.marinboy.dto.ServiceItemRequest;
import com.marinboy.marinboy.dto.ServicePriceUpdateRequest;
import com.marinboy.marinboy.model.ReservationStatus;
import com.marinboy.marinboy.service.DatabaseVerificationService;
import com.marinboy.marinboy.service.ImageStorageService;
import com.marinboy.marinboy.service.SalonReservationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// 예약관리 대시보드에서 사용하는 JSON API를 제공합니다.
@RestController
@RequestMapping("/api")
public class SalonReservationApiController {

    private final SalonReservationService salonReservationService;
    private final DatabaseVerificationService databaseVerificationService;
    private final ImageStorageService imageStorageService;

    public SalonReservationApiController(
            SalonReservationService salonReservationService,
            DatabaseVerificationService databaseVerificationService,
            ImageStorageService imageStorageService) {
        this.salonReservationService = salonReservationService;
        this.databaseVerificationService = databaseVerificationService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        // 서버가 정상 실행 중인지 간단히 확인하는 엔드포인트입니다.
        return Map.of("status", "ok", "project", "marinboy");
    }

    @GetMapping("/db-time")
    public DatabaseTimeResponse databaseTime() {
        // 현재 연결된 데이터베이스 시간을 반환해서 DB 연결 상태를 검증합니다.
        return databaseVerificationService.getDatabaseTime();
    }

    @GetMapping("/services")
    public List<SalonServiceResponse> services() {
        // 고객이 선택할 수 있는 미용실 시술 목록을 반환합니다.
        return salonReservationService.getServiceCatalog().stream()
                .map(this::toServiceResponse)
                .toList();
    }

    @GetMapping("/services/top3")
    public List<SalonServiceResponse> topServices() {
        // 로그인 후 바로 보여줄 이달의 추천 TOP3 메뉴를 반환합니다.
        return salonReservationService.getTopServices().stream()
                .map(this::toServiceResponse)
                .toList();
    }

    @PostMapping("/service-images")
    public ResponseEntity<ImageUploadResponse> uploadServiceImage(@RequestParam MultipartFile file) {
        // 관리자가 파일로 올린 시술 대표 이미지를 저장하고 화면에서 사용할 URL을 반환합니다.
        return ResponseEntity.ok(new ImageUploadResponse(imageStorageService.storeServiceImage(file)));
    }

    @PatchMapping("/services/{serviceId}/price")
    public ResponseEntity<SalonServiceResponse> updateServicePrice(
            @PathVariable Long serviceId,
            @Valid @RequestBody ServicePriceUpdateRequest request) {
        // 원장 관리 화면에서 시술 가격을 수정합니다.
        return ResponseEntity.ok(toServiceResponse(
                salonReservationService.updateServicePrice(serviceId, request.price())));
    }

    @PostMapping("/services")
    public ResponseEntity<SalonServiceResponse> createService(@Valid @RequestBody ServiceItemRequest request) {
        // 원장이 새 시술 메뉴를 등록합니다.
        return ResponseEntity.ok(toServiceResponse(salonReservationService.createServiceItem(request)));
    }

    @PutMapping("/services/{serviceId}")
    public ResponseEntity<SalonServiceResponse> updateService(
            @PathVariable Long serviceId,
            @Valid @RequestBody ServiceItemRequest request) {
        // 원장이 기존 시술 메뉴의 전체 정보를 수정합니다.
        return ResponseEntity.ok(toServiceResponse(salonReservationService.updateServiceItem(serviceId, request)));
    }

    @DeleteMapping("/services/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable Long serviceId) {
        // 예약 이력이 없는 시술 메뉴를 삭제합니다.
        salonReservationService.deleteServiceItem(serviceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/services/{serviceId}/available-slots")
    public AvailableSlotResponse availableSlots(
            @PathVariable Long serviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        // 특정 시술과 날짜를 기준으로 예약 가능한 시간대를 반환합니다.
        return salonReservationService.getAvailableSlots(serviceId, date);
    }

    @GetMapping("/reservations")
    public List<SalonReservationResponse> reservations() {
        // 관리 화면에 표시할 전체 예약 목록을 반환합니다.
        return salonReservationService.getReservations();
    }

    @GetMapping("/reservations/reminder-targets")
    public List<NoShowAlertResponse> reminderTargets() {
        // 노쇼 방지를 위해 알림 안내가 필요한 예약 목록을 반환합니다.
        return salonReservationService.getReminderTargets();
    }

    @GetMapping("/holidays")
    public List<HolidayResponse> holidays() {
        // 원장이 등록한 특정 휴무일 목록을 반환합니다.
        return salonReservationService.getHolidays();
    }

    @PostMapping("/holidays")
    public ResponseEntity<HolidayResponse> saveHoliday(@Valid @RequestBody HolidayRequest request) {
        // 특정 날짜를 휴무일로 등록하거나 기존 휴무 사유를 수정합니다.
        return ResponseEntity.ok(salonReservationService.saveHoliday(request));
    }

    @DeleteMapping("/holidays/{holidayDate}")
    public ResponseEntity<Void> deleteHoliday(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate holidayDate) {
        // 등록된 휴무일을 삭제해 다시 예약 가능 날짜로 되돌립니다.
        salonReservationService.deleteHoliday(holidayDate);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reservations")
    public ResponseEntity<SalonReservationResponse> createReservation(
            @Valid @RequestBody CreateReservationRequest request,
            HttpSession session) {
        // 입력값 검증 후 새로운 예약 요청을 생성합니다.
        return ResponseEntity.ok(salonReservationService.createReservation(request, currentUser(session)));
    }

    @PatchMapping("/reservations/{reservationId}/status")
    public ResponseEntity<SalonReservationResponse> updateStatus(
            @PathVariable Long reservationId,
            @Valid @RequestBody ReservationStatusUpdateRequest request) {
        // 원장 관리 화면에서 예약 상태를 변경합니다.
        ReservationStatus status = ReservationStatus.valueOf(request.status());
        return ResponseEntity.ok(salonReservationService.updateStatus(reservationId, status));
    }

    @GetMapping("/customers/history")
    public List<CustomerHistoryResponse> customerHistory(
            @RequestParam(required = false) String customerPhone,
            HttpSession session) {
        // 고객 화면에서 같은 고객의 이전 시술 이력을 확인합니다.
        return salonReservationService.getCustomerHistory(customerPhone, currentUser(session));
    }

    private SalonServiceResponse toServiceResponse(com.marinboy.marinboy.model.SalonServiceItem service) {
        // 엔티티의 내부 구조를 숨기고 화면에 필요한 값만 응답합니다.
        return new SalonServiceResponse(
                service.getId(),
                service.getName(),
                service.getCategory(),
                service.getDurationMinutes(),
                service.getPrice(),
                service.getDescription(),
                service.getImageUrl(),
                toAdditionalImageUrlList(service.getAdditionalImageUrls()),
                service.getTopRank());
    }

    private List<String> toAdditionalImageUrlList(String additionalImageUrls) {
        // DB에는 줄바꿈으로 저장하고 API에서는 프론트가 쓰기 쉬운 배열로 내려줍니다.
        if (additionalImageUrls == null || additionalImageUrls.isBlank()) {
            return List.of();
        }

        return Arrays.stream(additionalImageUrls.split("\\R"))
                .filter(url -> !url.isBlank())
                .toList();
    }

    private SessionUser currentUser(HttpSession session) {
        return (SessionUser) session.getAttribute(AuthSession.LOGIN_USER);
    }
}
