package com.marinboy.marinboy.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

// 원장이 시술 가격을 수정할 때 사용하는 요청 본문입니다.
public record ServicePriceUpdateRequest(
        @NotNull(message = "가격을 입력해야 합니다.")
        @DecimalMin(value = "0.0", inclusive = false, message = "가격은 0원보다 커야 합니다.")
        BigDecimal price) {
}
