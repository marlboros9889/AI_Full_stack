package com.marinboy.marinboy.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

// 원장이 특정 날짜를 휴무일로 등록할 때 사용하는 요청 본문입니다.
public record HolidayRequest(
        @NotNull(message = "휴무 날짜를 선택해야 합니다.") LocalDate holidayDate,
        @Size(max = 200, message = "휴무 사유는 200자 이하로 입력해야 합니다.") String reason) {
}
