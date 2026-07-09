package com.marinboy.marinboy.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

// 고객이 예약 폼을 제출할 때 사용하는 요청 본문입니다.
public record CreateReservationRequest(
        @NotNull(message = "시술을 선택해야 합니다.") Long serviceId,
        @NotBlank(message = "고객명을 입력해야 합니다.") String customerName,
        @Email(message = "이메일 형식이 올바르지 않습니다.") @NotBlank(message = "이메일을 입력해야 합니다.") String customerEmail,
        @NotBlank(message = "연락처를 입력해야 합니다.") String customerPhone,
        @NotNull(message = "예약 일시를 선택해야 합니다.") @Future(message = "예약 일시는 현재보다 이후여야 합니다.") LocalDateTime reservationDateTime,
        @AssertTrue(message = "노쇼 방지 예약 안내에 동의해야 합니다.") boolean noShowPolicyAgreed,
        String memo) {
}
