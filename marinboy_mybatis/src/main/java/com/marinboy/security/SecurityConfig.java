package com.marinboy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpStatus;

import com.marinboy.dto.UserDto;

import java.util.Map;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/** 세션 기반 자체 로그인과 REST 요청을 사용할 수 있도록 Spring Security를 설정합니다. */
@Configuration
public class SecurityConfig {
    // 관리자 및 DB 진단 경로는 세션의 관리자 역할을 확인한 뒤 접근을 허용합니다.
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // JSON·파일 업로드 API 호출 시 CSRF 토큰을 요구하지 않습니다.
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(sessionAuthenticationFilter(), AnonymousAuthenticationFilter.class)
                // 공개 고객 기능과 관리자 전용 기능을 URL 수준에서도 분리합니다.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**", "/api/admin/**", "/api/db/**")
                        .access(adminSessionAuthorization())
                        .anyRequest().permitAll())
                .exceptionHandling(exceptions -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                                new AntPathRequestMatcher("/api/**")))
                .oauth2Login(oauth -> oauth
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                            OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
                            Map<String, Object> account = oauthUser.getAttribute("kakao_account");
                            Map<String, Object> profile = account == null ? null : (Map<String, Object>) account.get("profile");

                            UserDto user = new UserDto();
                            user.setUsername("kakao_" + oauthUser.getName());
                            user.setName(profile == null ? "카카오 사용자" : String.valueOf(profile.get("nickname")));
                            user.setEmail(account == null ? null : (String) account.get("email"));
                            user.setRole(SecurityConstants.ROLE_CUSTOMER);
                            request.getSession().setAttribute(SecurityConstants.LOGIN_USER, user);
                            response.sendRedirect("/");
                        })
                        .failureHandler((request, response, exception) -> {
                            String message = URLEncoder.encode(exception.getMessage(), StandardCharsets.UTF_8);
                            response.sendRedirect("/login?oauthError=" + message);
                        }))
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private OncePerRequestFilter sessionAuthenticationFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(
                    jakarta.servlet.http.HttpServletRequest request,
                    jakarta.servlet.http.HttpServletResponse response,
                    jakarta.servlet.FilterChain filterChain
            ) throws java.io.IOException, jakarta.servlet.ServletException {
                var session = request.getSession(false);
                Object loginUser = session == null ? null : session.getAttribute(SecurityConstants.LOGIN_USER);
                if (loginUser instanceof UserDto user
                        && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, List.of(authority));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    private AuthorizationManager<RequestAuthorizationContext> adminSessionAuthorization() {
        return (authentication, context) -> {
            var session = context.getRequest().getSession(false);
            Object loginUser = session == null ? null : session.getAttribute(SecurityConstants.LOGIN_USER);
            boolean admin = loginUser instanceof UserDto user
                    && SecurityConstants.ROLE_ADMIN.equals(user.getRole());
            return new AuthorizationDecision(admin);
        };
    }
}
