package com.marinboy.dto;

// DB 연결 검증 API가 반환하는 데이터 구조입니다.
public record DatabaseTimeResponse(
        String vendor,
        String databaseTime) {
}
