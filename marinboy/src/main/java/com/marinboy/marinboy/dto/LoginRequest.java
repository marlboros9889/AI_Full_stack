package com.marinboy.marinboy.dto;

import jakarta.validation.constraints.NotBlank;

// 로그인 폼과 API에서 사용하는 요청 본문입니다.
public record LoginRequest(
        @NotBlank(message = "아이디를 입력해야 합니다.") String username,
        @NotBlank(message = "비밀번호를 입력해야 합니다.") String password) {
}
