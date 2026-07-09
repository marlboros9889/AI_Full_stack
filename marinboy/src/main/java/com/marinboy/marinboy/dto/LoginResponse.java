package com.marinboy.marinboy.dto;

// 로그인 성공 후 프론트에서 역할을 확인할 때 사용하는 응답입니다.
public record LoginResponse(
        String username,
        String displayName,
        String role,
        String email) {
}
