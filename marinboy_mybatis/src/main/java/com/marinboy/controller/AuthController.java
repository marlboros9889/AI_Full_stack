package com.marinboy.controller;

import com.marinboy.dto.UserDto;
import com.marinboy.security.SecurityConstants;
import com.marinboy.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }
    @GetMapping("/login") public String loginPage() { return "login"; }
    @RestController
    static class AuthApiController {
        private final AuthService authService;
        AuthApiController(AuthService authService) { this.authService = authService; }
        @PostMapping("/api/auth/login")
        ResponseEntity<UserDto> login(@RequestBody UserDto request, HttpSession session) {
            UserDto user = authService.login(request.getUsername(), request.getPassword());
            user.setDisplayName(user.getName());
            session.setAttribute(SecurityConstants.LOGIN_USER, user);
            return ResponseEntity.ok(user);
        }
    }
}
