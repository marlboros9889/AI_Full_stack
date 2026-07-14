package com.marinboy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/** 세션 기반 자체 로그인과 REST 요청을 사용할 수 있도록 Spring Security를 설정합니다. */
@Configuration
public class SecurityConfig {
    // 권한은 컨트롤러에서 세션으로 검사하므로 Security 필터에서는 URL 접근을 허용합니다.
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // JSON·파일 업로드 API 호출 시 CSRF 토큰을 요구하지 않습니다.
                .csrf(AbstractHttpConfigurer::disable)
                // 고객과 관리자 구분은 각 컨트롤러의 세션 검사에서 처리합니다.
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .build();
    }
}
