package com.marinboy.marinboy.auth;

// 세션에 저장할 최소 로그인 사용자 정보입니다.
public record SessionUser(
        String username,
        String displayName,
        AuthRole role,
        String email,
        String socialProvider,
        String socialId) {

    public SessionUser(String username, String displayName, AuthRole role) {
        this(username, displayName, role, null, null, null);
    }
}
