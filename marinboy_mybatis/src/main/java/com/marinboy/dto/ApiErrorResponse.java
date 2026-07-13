package com.marinboy.dto;

// API 오류가 발생했을 때 화면에서 읽을 수 있는 메시지로 내려주는 응답 DTO입니다.
public record ApiErrorResponse(String message) {
}
