package com.marinboy.marinboy.dto;

import com.marinboy.marinboy.model.ReservationStatus;
import java.time.LocalDateTime;

// 고객의 이전 시술 이력을 고객 화면과 관리자 화면에 보여줄 때 사용하는 응답입니다.
public record CustomerHistoryResponse(
        Long reservationId,
        String serviceName,
        String serviceCategory,
        LocalDateTime reservationDateTime,
        ReservationStatus status,
        String memo) {
}
