package com.marinboy.marinboy.dto;

import java.math.BigDecimal;
import java.util.List;

// 시술 카탈로그에 표시할 각 시술 정보를 담는 응답 객체입니다.
public record SalonServiceResponse(
        Long id,
        String name,
        String category,
        int durationMinutes,
        BigDecimal price,
        String description,
        String imageUrl,
        List<String> additionalImageUrls,
        Integer topRank) {
}
