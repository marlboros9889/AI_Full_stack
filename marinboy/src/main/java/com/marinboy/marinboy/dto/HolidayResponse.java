package com.marinboy.marinboy.dto;

import java.time.LocalDate;

// 관리 화면에 표시할 휴무일 정보를 담는 응답 객체입니다.
public record HolidayResponse(
        LocalDate holidayDate,
        String reason) {
}
