package com.marinboy.marinboy.dto;

// 데이터베이스 연결 상태와 현재 DB 시간을 확인할 때 반환하는 응답 객체입니다.
public record DatabaseTimeResponse(
        String vendor,
        String validationQuery,
        String databaseTime) {
}
