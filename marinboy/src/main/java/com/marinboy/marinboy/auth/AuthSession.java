package com.marinboy.marinboy.auth;

// 세션 키를 한 곳에서 관리해 오타로 인한 인증 누락을 막습니다.
public final class AuthSession {

    public static final String LOGIN_USER = "LOGIN_USER";

    private AuthSession() {
    }
}
