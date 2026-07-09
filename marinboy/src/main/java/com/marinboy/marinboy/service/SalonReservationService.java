package com.marinboy.marinboy.service;

import com.marinboy.marinboy.auth.SessionUser;
import com.marinboy.marinboy.dto.AvailableSlotResponse;
import com.marinboy.marinboy.dto.CreateReservationRequest;
import com.marinboy.marinboy.dto.CustomerHistoryResponse;
import com.marinboy.marinboy.dto.HolidayRequest;
import com.marinboy.marinboy.dto.HolidayResponse;
import com.marinboy.marinboy.dto.NoShowAlertResponse;
import com.marinboy.marinboy.dto.SalonReservationResponse;
import com.marinboy.marinboy.dto.ServiceItemRequest;
import com.marinboy.marinboy.model.ReservationStatus;
import com.marinboy.marinboy.model.SalonCustomer;
import com.marinboy.marinboy.model.SalonHoliday;
import com.marinboy.marinboy.model.SalonReservation;
import com.marinboy.marinboy.model.SalonServiceItem;
import com.marinboy.marinboy.repository.SalonCustomerRepository;
import com.marinboy.marinboy.repository.SalonHolidayRepository;
import com.marinboy.marinboy.repository.SalonReservationRepository;
import com.marinboy.marinboy.repository.SalonServiceItemRepository;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 미용실 예약과 관련된 핵심 비즈니스 로직을 담당합니다.
@Service
public class SalonReservationService {

    private static final LocalTime OPEN_TIME = LocalTime.of(10, 0);
    private static final LocalTime CLOSE_TIME = LocalTime.of(20, 0);
    private static final int RESERVATION_SLOT_MINUTES = 30;
    private static final int REMINDER_LOOKAHEAD_DAYS = 2;
    private static final int MAX_ADDITIONAL_IMAGE_COUNT = 3;
    private static final Pattern SERVICE_IMAGE_URL_PATTERN = Pattern.compile("^(https?://.+|/uploads/service-images/.+)");

    private final SalonReservationRepository salonReservationRepository;
    private final SalonServiceItemRepository salonServiceItemRepository;
    private final SalonHolidayRepository salonHolidayRepository;
    private final SalonCustomerRepository salonCustomerRepository;

    public SalonReservationService(
            SalonReservationRepository salonReservationRepository,
            SalonServiceItemRepository salonServiceItemRepository,
            SalonHolidayRepository salonHolidayRepository,
            SalonCustomerRepository salonCustomerRepository) {
        this.salonReservationRepository = salonReservationRepository;
        this.salonServiceItemRepository = salonServiceItemRepository;
        this.salonHolidayRepository = salonHolidayRepository;
        this.salonCustomerRepository = salonCustomerRepository;
    }

    @Transactional(readOnly = true)
    public List<SalonServiceItem> getServiceCatalog() {
        // 화면의 시술 목록과 선택 박스에 표시할 시술 정보를 반환합니다.
        return salonServiceItemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<SalonServiceItem> getTopServices() {
        // 원장이 지정한 이달의 추천 TOP3 메뉴를 순위 순서대로 반환합니다.
        return salonServiceItemRepository.findAll().stream()
                .filter(serviceItem -> serviceItem.getTopRank() != null)
                .sorted(Comparator.comparing(SalonServiceItem::getTopRank))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SalonReservationResponse> getReservations() {
        // 프론트 API에서 바로 사용할 수 있도록 변환된 예약 목록을 반환합니다.
        return salonReservationRepository.findAllByOrderByReservationDateTimeAsc().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<HolidayResponse> getHolidays() {
        // 원장이 등록한 휴무일을 관리 화면에서 사용할 응답 형태로 반환합니다.
        return salonHolidayRepository.findAllByOrderByHolidayDateAsc().stream()
                .map(holiday -> new HolidayResponse(holiday.getHolidayDate(), holiday.getReason()))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<NoShowAlertResponse> getReminderTargets() {
        // 앞으로 가까운 예약 중 고객에게 리마인드 안내가 필요한 예약을 조회합니다.
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderEnd = now.plusDays(REMINDER_LOOKAHEAD_DAYS);
        return salonReservationRepository
                .findByStatusInAndReservationDateTimeBetweenOrderByReservationDateTimeAsc(
                        List.of(ReservationStatus.REQUESTED, ReservationStatus.CONFIRMED),
                        now,
                        reminderEnd)
                .stream()
                .map(this::toNoShowAlertResponse)
                .toList();
    }

    @Transactional
    public SalonServiceItem updateServicePrice(Long serviceId, BigDecimal price) {
        // 관리자 화면에서 수정한 가격을 시술 카탈로그에 즉시 반영합니다.
        SalonServiceItem serviceItem = findServiceItem(serviceId);
        serviceItem.setPrice(price);
        return serviceItem;
    }

    @Transactional
    public SalonServiceItem createServiceItem(ServiceItemRequest request) {
        // 원장이 고객에게 노출할 새 시술 메뉴를 등록합니다.
        SalonServiceItem serviceItem = new SalonServiceItem();
        applyServiceItemRequest(serviceItem, request);
        return salonServiceItemRepository.save(serviceItem);
    }

    @Transactional
    public SalonServiceItem updateServiceItem(Long serviceId, ServiceItemRequest request) {
        // 기존 시술 메뉴의 이름, 카테고리, 시간, 가격, 설명, 대표 이미지를 수정합니다.
        SalonServiceItem serviceItem = findServiceItem(serviceId);
        applyServiceItemRequest(serviceItem, request);
        return serviceItem;
    }

    @Transactional
    public void deleteServiceItem(Long serviceId) {
        // 이미 예약 이력이 있는 시술은 과거 기록 보존을 위해 삭제하지 않습니다.
        if (salonReservationRepository.countByServiceId(serviceId) > 0) {
            throw new IllegalStateException("예약 이력이 있는 시술은 삭제할 수 없습니다. 메뉴 수정으로 비노출 처리해 주세요.");
        }
        salonServiceItemRepository.delete(findServiceItem(serviceId));
    }

    @Transactional
    public SalonReservationResponse createReservation(CreateReservationRequest request) {
        return createReservation(request, null);
    }

    @Transactional
    public SalonReservationResponse createReservation(CreateReservationRequest request, SessionUser sessionUser) {
        // 선택한 시술의 소요 시간과 검증 규칙을 알기 위해 시술 정보를 조회합니다.
        SalonServiceItem serviceItem = findServiceItem(request.serviceId());

        LocalDateTime start = request.reservationDateTime();
        LocalDateTime end = start.plusMinutes(serviceItem.getDurationMinutes());

        validateBusinessHours(start, end);
        validateHoliday(start.toLocalDate());
        validateNoShowHistory(request.customerPhone());

        // 요청한 시간이 진행 중인 예약과 겹치는지 확인합니다.
        if (hasConflict(serviceItem, start, end)) {
            throw new IllegalStateException("이미 예약이 있는 시간대입니다.");
        }

        SalonCustomer customer = findOrCreateCustomer(request, sessionUser);

        // 새 예약 요청을 원장 확인 대기 상태로 저장합니다.
        SalonReservation reservation = new SalonReservation();
        reservation.setService(serviceItem);
        reservation.setCustomer(customer);
        reservation.setCustomerName(request.customerName());
        reservation.setCustomerEmail(request.customerEmail());
        reservation.setCustomerPhone(request.customerPhone());
        reservation.setReservationDateTime(start);
        reservation.setNoShowPolicyAgreed(request.noShowPolicyAgreed());
        reservation.setStatus(ReservationStatus.REQUESTED);
        reservation.setMemo(request.memo());

        return toResponse(salonReservationRepository.save(reservation));
    }

    @Transactional(readOnly = true)
    public List<CustomerHistoryResponse> getCustomerHistory(String customerPhone, SessionUser sessionUser) {
        // 소셜 로그인 고객은 소셜 ID를 우선 사용하고, 일반 고객은 연락처로 이전 시술을 찾습니다.
        if (sessionUser != null && sessionUser.socialProvider() != null && sessionUser.socialId() != null) {
            return salonCustomerRepository
                    .findBySocialProviderAndSocialId(sessionUser.socialProvider(), sessionUser.socialId())
                    .map(customer -> salonReservationRepository.findByCustomerOrderByReservationDateTimeDesc(customer).stream()
                            .map(this::toCustomerHistoryResponse)
                            .toList())
                    .orElseGet(List::of);
        }

        if (customerPhone == null || customerPhone.isBlank()) {
            return List.of();
        }

        return salonReservationRepository.findByCustomerPhoneOrderByReservationDateTimeDesc(customerPhone).stream()
                .map(this::toCustomerHistoryResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AvailableSlotResponse getAvailableSlots(Long serviceId, LocalDate date) {
        // 특정 시술과 날짜를 기준으로 예약 가능한 시작 시간을 계산합니다.
        SalonServiceItem serviceItem = findServiceItem(serviceId);
        List<LocalDateTime> availableSlots = new ArrayList<>();

        if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return new AvailableSlotResponse(date, availableSlots);
        }

        if (salonHolidayRepository.existsById(date)) {
            return new AvailableSlotResponse(date, availableSlots);
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime slot = date.atTime(OPEN_TIME);
        LocalTime lastStartTime = CLOSE_TIME.minusMinutes(serviceItem.getDurationMinutes());

        while (!slot.toLocalTime().isAfter(lastStartTime)) {
            LocalDateTime slotEnd = slot.plusMinutes(serviceItem.getDurationMinutes());

            // 지난 시간은 숨기고 비즈니스 규칙을 통과한 시간대만 남깁니다.
            if (slot.isAfter(now) && !hasConflict(serviceItem, slot, slotEnd)) {
                availableSlots.add(slot);
            }

            slot = slot.plusMinutes(RESERVATION_SLOT_MINUTES);
        }

        return new AvailableSlotResponse(date, availableSlots);
    }

    @Transactional
    public SalonReservationResponse updateStatus(Long reservationId, ReservationStatus status) {
        // 원장 관리 화면에서 예약 상태를 변경합니다.
        SalonReservation reservation = salonReservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("예약 정보를 찾을 수 없습니다."));
        validateStatusTransition(reservation.getStatus(), status);
        reservation.setStatus(status);
        return toResponse(reservation);
    }

    @Transactional
    public HolidayResponse saveHoliday(HolidayRequest request) {
        // 이미 등록된 날짜라면 사유만 갱신하고, 새 날짜라면 휴무일을 새로 등록합니다.
        SalonHoliday holiday = salonHolidayRepository.findById(request.holidayDate())
                .orElseGet(SalonHoliday::new);
        holiday.setHolidayDate(request.holidayDate());
        holiday.setReason(request.reason());
        SalonHoliday savedHoliday = salonHolidayRepository.save(holiday);
        return new HolidayResponse(savedHoliday.getHolidayDate(), savedHoliday.getReason());
    }

    @Transactional
    public void deleteHoliday(LocalDate holidayDate) {
        // 원장이 실수로 등록한 휴무일을 되돌릴 수 있게 삭제합니다.
        salonHolidayRepository.deleteById(holidayDate);
    }

    private void validateBusinessHours(LocalDateTime start, LocalDateTime end) {
        // 정기 휴무일인 일요일 예약을 막습니다.
        if (start.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new IllegalStateException("일요일은 예약을 받지 않습니다.");
        }

        // 원장 일정 관리가 깔끔하도록 30분 단위 예약만 허용합니다.
        if (start.getMinute() % RESERVATION_SLOT_MINUTES != 0 || start.getSecond() != 0 || start.getNano() != 0) {
            throw new IllegalStateException("예약은 30분 단위로만 등록할 수 있습니다.");
        }

        // 예약 시간이 영업시간 안에 완전히 들어오는지 확인합니다.
        if (start.toLocalTime().isBefore(OPEN_TIME) || end.toLocalTime().isAfter(CLOSE_TIME)) {
            throw new IllegalStateException("영업시간은 10:00부터 20:00까지입니다.");
        }
    }

    private void validateHoliday(LocalDate date) {
        // 원장이 별도로 지정한 휴무일에는 예약 생성 자체를 막습니다.
        if (salonHolidayRepository.existsById(date)) {
            throw new IllegalStateException("선택한 날짜는 휴무일입니다.");
        }
    }

    private void validateNoShowHistory(String customerPhone) {
        // 같은 연락처에 노쇼 이력이 있으면 원장 확인 없이 새 예약을 받지 않습니다.
        if (salonReservationRepository.countByCustomerPhoneAndStatus(customerPhone, ReservationStatus.NO_SHOW) > 0) {
            throw new IllegalStateException("노쇼 이력이 있는 연락처는 관리자에게 문의 후 예약할 수 있습니다.");
        }
    }

    private void validateStatusTransition(ReservationStatus currentStatus, ReservationStatus nextStatus) {
        // 예약 상태는 운영 흐름에 맞게만 변경되도록 제한합니다.
        if (currentStatus == nextStatus) {
            return;
        }

        if (currentStatus == ReservationStatus.REQUESTED
                && (nextStatus == ReservationStatus.CONFIRMED || nextStatus == ReservationStatus.CANCELED)) {
            return;
        }

        if (currentStatus == ReservationStatus.CONFIRMED
                && (nextStatus == ReservationStatus.COMPLETED || nextStatus == ReservationStatus.NO_SHOW)) {
            return;
        }

        throw new IllegalStateException("현재 예약 상태에서는 선택한 상태로 변경할 수 없습니다.");
    }

    private SalonServiceItem findServiceItem(Long serviceId) {
        // 시술 조회 실패 처리를 한 곳에서 동일하게 관리합니다.
        return salonServiceItemRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("선택한 시술을 찾을 수 없습니다."));
    }

    private void applyServiceItemRequest(SalonServiceItem serviceItem, ServiceItemRequest request) {
        // 메뉴 등록과 수정에서 같은 필드 매핑을 사용합니다.
        validateServiceItemUnit(request);
        serviceItem.setName(request.name());
        serviceItem.setCategory(request.category());
        serviceItem.setDurationMinutes(request.durationMinutes());
        serviceItem.setPrice(request.price());
        serviceItem.setDescription(request.description());
        serviceItem.setImageUrl(request.imageUrl());
        serviceItem.setAdditionalImageUrls(joinAdditionalImageUrls(request.additionalImageUrls()));
        applyTopRank(serviceItem, request.topRank());
    }

    private void validateServiceItemUnit(ServiceItemRequest request) {
        // 실제 미용실 운영 기준에 맞춰 시술 시간은 10분 단위, 가격은 1,000원 단위로만 받습니다.
        if (request.durationMinutes() % 10 != 0) {
            throw new IllegalArgumentException("시술 시간은 10분 단위로 입력해야 합니다.");
        }

        if (request.price().remainder(BigDecimal.valueOf(1000)).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("시술 가격은 1,000원 단위로 입력해야 합니다.");
        }
    }

    private void applyTopRank(SalonServiceItem serviceItem, Integer topRank) {
        // TOP3 순위는 1~3만 허용하고, 같은 순위가 있으면 기존 메뉴의 순위를 자동 해제합니다.
        if (topRank == null) {
            serviceItem.setTopRank(null);
            return;
        }

        if (topRank < 1 || topRank > 3) {
            throw new IllegalArgumentException("이달의 추천 순위는 1위부터 3위까지만 선택할 수 있습니다.");
        }

        salonServiceItemRepository.findByTopRank(topRank)
                .filter(existing -> !existing.getId().equals(serviceItem.getId()))
                .ifPresent(existing -> existing.setTopRank(null));
        serviceItem.setTopRank(topRank);
    }

    private String joinAdditionalImageUrls(List<String> additionalImageUrls) {
        // 대표 이미지 외 상세 이미지는 최대 3장까지만 저장해 화면이 과하게 길어지지 않게 합니다.
        if (additionalImageUrls == null) {
            return null;
        }

        List<String> cleanedUrls = additionalImageUrls.stream()
                .filter(url -> url != null && !url.isBlank())
                .map(String::trim)
                .toList();

        if (cleanedUrls.size() > MAX_ADDITIONAL_IMAGE_COUNT) {
            throw new IllegalArgumentException("추가 이미지는 최대 3장까지만 등록할 수 있습니다.");
        }

        for (String url : cleanedUrls) {
            if (url.length() > 500 || !SERVICE_IMAGE_URL_PATTERN.matcher(url).matches()) {
                throw new IllegalArgumentException("추가 이미지는 URL 또는 업로드 파일 경로만 사용할 수 있습니다.");
            }
        }

        return cleanedUrls.isEmpty() ? null : String.join("\n", cleanedUrls);
    }

    private SalonCustomer findOrCreateCustomer(CreateReservationRequest request, SessionUser sessionUser) {
        // 같은 고객을 소셜 ID, 연락처, 이메일 순서로 찾아 예약 이력을 한 고객에게 모읍니다.
        SalonCustomer customer = null;
        if (sessionUser != null && sessionUser.socialProvider() != null && sessionUser.socialId() != null) {
            customer = salonCustomerRepository
                    .findBySocialProviderAndSocialId(sessionUser.socialProvider(), sessionUser.socialId())
                    .orElse(null);
        }
        if (customer == null) {
            customer = salonCustomerRepository.findByPhone(request.customerPhone()).orElse(null);
        }
        if (customer == null) {
            customer = salonCustomerRepository.findByEmail(request.customerEmail()).orElse(null);
        }

        LocalDateTime now = LocalDateTime.now();
        if (customer == null) {
            customer = new SalonCustomer();
            customer.setCreatedAt(now);
        }

        customer.setName(request.customerName());
        customer.setEmail(request.customerEmail());
        customer.setPhone(request.customerPhone());
        if (sessionUser != null && sessionUser.socialProvider() != null && sessionUser.socialId() != null) {
            customer.setSocialProvider(sessionUser.socialProvider());
            customer.setSocialId(sessionUser.socialId());
        }
        customer.setUpdatedAt(now);
        return salonCustomerRepository.save(customer);
    }

    private boolean hasConflict(SalonServiceItem serviceItem, LocalDateTime start, LocalDateTime end) {
        // 예약 생성과 가능 시간 조회에서 같은 중복 검사 로직을 재사용합니다.
        return salonReservationRepository
                .findByStatusInAndReservationDateTimeBetween(
                        List.of(ReservationStatus.REQUESTED, ReservationStatus.CONFIRMED),
                        start.minusMinutes(serviceItem.getDurationMinutes() - 1),
                        end.minusMinutes(1))
                .stream()
                .anyMatch(existing -> overlaps(existing, start, end));
    }

    private boolean overlaps(SalonReservation existing, LocalDateTime start, LocalDateTime end) {
        // 두 예약 시간 구간이 서로 겹치는지 비교합니다.
        LocalDateTime existingStart = existing.getReservationDateTime();
        LocalDateTime existingEnd = existingStart.plusMinutes(existing.getService().getDurationMinutes());
        return start.isBefore(existingEnd) && end.isAfter(existingStart);
    }

    private SalonReservationResponse toResponse(SalonReservation reservation) {
        // 엔티티를 프론트에서 사용하는 응답 형태로 변환합니다.
        return new SalonReservationResponse(
                reservation.getId(),
                reservation.getService().getName(),
                reservation.getService().getDurationMinutes(),
                reservation.getCustomerName(),
                reservation.getCustomerEmail(),
                reservation.getCustomerPhone(),
                reservation.getReservationDateTime(),
                reservation.getStatus(),
                reservation.isNoShowPolicyAgreed(),
                reservation.getMemo());
    }

    private NoShowAlertResponse toNoShowAlertResponse(SalonReservation reservation) {
        // 실제 문자 발송 연동 전에도 관리자가 알림 대상자를 확인할 수 있게 응답을 만듭니다.
        return new NoShowAlertResponse(
                reservation.getId(),
                reservation.getCustomerName(),
                reservation.getCustomerPhone(),
                reservation.getService().getName(),
                reservation.getReservationDateTime(),
                "예약 전날 또는 당일에 시간 확인 안내가 필요합니다.");
    }

    private CustomerHistoryResponse toCustomerHistoryResponse(SalonReservation reservation) {
        // 고객 이력 화면에서 필요한 시술 결과만 간단히 반환합니다.
        return new CustomerHistoryResponse(
                reservation.getId(),
                reservation.getService().getName(),
                reservation.getService().getCategory(),
                reservation.getReservationDateTime(),
                reservation.getStatus(),
                reservation.getMemo());
    }
}
