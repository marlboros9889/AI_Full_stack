package com.marinboy.marinboy.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

// 원장이 시술 메뉴를 새로 만들거나 수정할 때 사용하는 요청 본문입니다.
public record ServiceItemRequest(
        @NotBlank(message = "시술명을 입력해야 합니다.") @Size(max = 100, message = "시술명은 100자 이하로 입력해야 합니다.") String name,
        @NotBlank(message = "카테고리를 입력해야 합니다.") @Size(max = 30, message = "카테고리는 30자 이하로 입력해야 합니다.") String category,
        @Min(value = 10, message = "시술 시간은 최소 10분 이상이어야 합니다.") int durationMinutes,
        @NotNull(message = "가격을 입력해야 합니다.")
        @DecimalMin(value = "1000.0", inclusive = true, message = "가격은 최소 1,000원 이상이어야 합니다.")
        BigDecimal price,
        @NotBlank(message = "시술 설명을 입력해야 합니다.") @Size(max = 255, message = "시술 설명은 255자 이하로 입력해야 합니다.") String description,
        @NotBlank(message = "시술 이미지 URL을 입력해야 합니다.")
        @Size(max = 500, message = "시술 이미지 URL은 500자 이하로 입력해야 합니다.")
        @Pattern(regexp = "^(https?://.+|/uploads/service-images/.+)", message = "시술 이미지는 URL 또는 업로드 파일 경로여야 합니다.")
        String imageUrl,
        List<String> additionalImageUrls,
        Integer topRank) {
}
