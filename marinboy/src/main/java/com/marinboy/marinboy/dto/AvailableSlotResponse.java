package com.marinboy.marinboy.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// 특정 날짜의 예약 가능 시간 목록을 담는 응답 객체입니다.
public record AvailableSlotResponse(
        LocalDate date,
        List<LocalDateTime> availableSlots) {
}
