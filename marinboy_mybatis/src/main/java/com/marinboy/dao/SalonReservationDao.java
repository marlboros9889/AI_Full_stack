package com.marinboy.dao;

import com.marinboy.dto.ReservationDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

// 고객 예약 등록, 중복 예약 확인, 이전 시술 이력을 처리하는 MyBatis DAO입니다.
@Mapper
public interface SalonReservationDao {

    // 연락처가 같은 고객이 이미 있으면 기존 고객 ID를 사용합니다.
    Long findCustomerIdByPhone(@Param("customerPhone") String customerPhone);

    // 처음 예약하는 고객을 MB_USER에 CUSTOMER 역할로 저장합니다.
    void insertCustomer(
            @Param("username") String username,
            @Param("password") String password,
            @Param("name") String name,
            @Param("email") String email,
            @Param("phone") String phone
    );

    // 방금 저장한 고객 ID를 연락처 기준으로 다시 조회합니다.
    Long findInsertedCustomerId(@Param("customerPhone") String customerPhone);

    // 같은 시간대의 유효 예약이 있는지 확인해 중복 예약을 막습니다.
    // 관리자가 지정한 휴무일인지 확인해 해당 날짜 예약을 막습니다.
    int countOverlappingReservation(
            @Param("serviceId") Long serviceId,
            @Param("reservationDateTime") LocalDateTime reservationDateTime
    );

    int countHoliday(@Param("reservationDate") LocalDate reservationDate);
    List<LocalDate> findHolidays();
    int saveHoliday(@Param("holidayDate") LocalDate holidayDate, @Param("reason") String reason);
    int deleteHoliday(@Param("holidayDate") LocalDate holidayDate);
    // 고객이 작성한 예약 요청을 REQUESTED 상태로 저장합니다.
    void insertReservation(
            @Param("serviceId") Long serviceId,
            @Param("customerId") Long customerId,
            @Param("customerName") String customerName,
            @Param("customerEmail") String customerEmail,
            @Param("customerPhone") String customerPhone,
            @Param("reservationDateTime") LocalDateTime reservationDateTime,
            @Param("noShowPolicyAgreed") int noShowPolicyAgreed,
            @Param("memo") String memo
    );

    // 고객 연락처 기준으로 이전 시술 이력을 최신순으로 조회합니다.
    List<ReservationDto> findCustomerHistory(@Param("customerPhone") String customerPhone);

    // 관리자 화면에서 사용할 예약 목록을 조회합니다.
    List<ReservationDto> findAllReservations();
    int countReservations();
    List<ReservationDto> findReservationsPage(@Param("offset") int offset, @Param("size") int size);
    ReservationDto findReservationById(@Param("reservationId") Long reservationId);
    List<ReservationDto> findReminderTargets();

    int updateReservationStatus(@Param("reservationId") Long reservationId, @Param("status") String status, @Param("rejectionReason") String rejectionReason);
}
