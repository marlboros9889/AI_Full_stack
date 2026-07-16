package com.marinboy.marinboy.service;

import com.marinboy.marinboy.auth.AuthRole;
import com.marinboy.marinboy.auth.SessionUser;
import org.springframework.stereotype.Service;

// 포트폴리오 MVP용 고정 계정 로그인을 담당합니다.
@Service
public class AuthService {

    public SessionUser login(String username, String password) {
        // DB 회원 테이블을 붙이기 전까지는 데모용 고정 계정으로 역할 분리를 검증합니다.
        if ("customer".equals(username) && "customer123".equals(password)) {
            return new SessionUser("customer", "고객 사용자", AuthRole.CUSTOMER);
        }

        if ("admin".equals(username) && "admin123".equals(password)) {
            return new SessionUser("admin", "원장 관리자", AuthRole.ADMIN);
        }

        throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
    }
}
