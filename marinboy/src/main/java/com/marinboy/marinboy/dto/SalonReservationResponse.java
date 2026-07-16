package com.marinboy.marinboy.dto;

import com.marinboy.marinboy.model.ReservationStatus;
import java.time.LocalDateTime;

// 대시보드의 예약 목록 한 줄에 표시할 응답 객체입니다.
public record SalonReservationResponse(
        Long id,
        String serviceName,
        int durationMinutes,
        String customerName,
        String customerEmail,
        String customerPhone,
        LocalDateTime reservationDateTime,
        ReservationStatus status,
        boolean noShowPolicyAgreed,
        String memo) {
}
