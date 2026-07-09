package com.marinboy.marinboy.dto;

import java.time.LocalDateTime;

// 관리자 화면에서 예약 알림이 필요한 고객을 표시할 때 사용하는 응답 객체입니다.
public record NoShowAlertResponse(
        Long reservationId,
        String customerName,
        String customerPhone,
        String serviceName,
        LocalDateTime reservationDateTime,
        String message) {
}
