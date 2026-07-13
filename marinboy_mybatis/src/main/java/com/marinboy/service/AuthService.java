package com.marinboy.service;

import com.marinboy.dao.AuthDao;
import com.marinboy.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthDao authDao;
    public AuthService(AuthDao authDao) { this.authDao = authDao; }
    public UserDto login(String username, String password) {
        if (username == null || password == null) throw new IllegalArgumentException("아이디와 비밀번호를 입력하세요.");
        UserDto user = authDao.findByCredentials(username.trim(), password);
        if (user == null) throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        return user;
    }
}
