package com.marinboy.marinboy.repository;

import com.marinboy.marinboy.model.ReservationStatus;
import com.marinboy.marinboy.model.SalonCustomer;
import com.marinboy.marinboy.model.SalonReservation;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// 예약 엔티티를 조회하고 저장하는 Repository입니다.
public interface SalonReservationRepository extends JpaRepository<SalonReservation, Long> {

    // 대시보드 표시를 위해 예약을 시간순으로 조회합니다.
    List<SalonReservation> findAllByOrderByReservationDateTimeAsc();

    // 예약 중복 확인을 위해 지정한 시간 범위의 활성 예약을 조회합니다.
    List<SalonReservation> findByStatusInAndReservationDateTimeBetween(
            List<ReservationStatus> statuses, LocalDateTime from, LocalDateTime to);

    // 노쇼 이력이 있는 연락처인지 확인합니다.
    long countByCustomerPhoneAndStatus(String customerPhone, ReservationStatus status);

    // 시술 메뉴 삭제 전 기존 예약에서 사용 중인지 확인합니다.
    long countByServiceId(Long serviceId);

    // 고객의 이전 시술 이력을 최신 예약순으로 조회합니다.
    List<SalonReservation> findByCustomerOrderByReservationDateTimeDesc(SalonCustomer customer);

    // 연락처 기준으로 고객의 이전 시술 이력을 최신 예약순으로 조회합니다.
    List<SalonReservation> findByCustomerPhoneOrderByReservationDateTimeDesc(String customerPhone);

    // 예약 알림 대상자를 날짜순으로 조회합니다.
    List<SalonReservation> findByStatusInAndReservationDateTimeBetweenOrderByReservationDateTimeAsc(
            List<ReservationStatus> statuses, LocalDateTime from, LocalDateTime to);
}
