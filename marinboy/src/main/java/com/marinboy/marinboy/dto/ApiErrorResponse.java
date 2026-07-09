package com.marinboy.marinboy.dto;

import java.time.LocalDateTime;

// 예약 API 요청이 실패했을 때 반환하는 표준 에러 응답입니다.
public record ApiErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path) {
}
