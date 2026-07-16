package com.marinboy.marinboy.dto;

import jakarta.validation.constraints.NotBlank;

// 원장이 예약 상태를 변경할 때 사용하는 요청 본문입니다.
public record ReservationStatusUpdateRequest(@NotBlank(message = "변경할 예약 상태를 입력해야 합니다.") String status) {
}
