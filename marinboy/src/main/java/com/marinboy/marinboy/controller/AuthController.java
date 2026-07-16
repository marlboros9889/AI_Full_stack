package com.marinboy.marinboy.controller;

import com.marinboy.marinboy.auth.AuthSession;
import com.marinboy.marinboy.auth.SessionUser;
import com.marinboy.marinboy.dto.LoginRequest;
import com.marinboy.marinboy.dto.LoginResponse;
import com.marinboy.marinboy.dto.SocialLoginProviderResponse;
import com.marinboy.marinboy.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

// 세션 기반 로그인과 로그아웃을 처리합니다.
@Controller
public class AuthController {

    private final AuthService authService;
    private final ObjectProvider<ClientRegistrationRepository> clientRegistrationRepository;

    public AuthController(
            AuthService authService,
            ObjectProvider<ClientRegistrationRepository> clientRegistrationRepository) {
        this.authService = authService;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        // 고객과 관리자가 같은 로그인 화면에서 역할 계정으로 접속합니다.
        return "login";
    }

    @PostMapping("/api/auth/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session) {
        // 로그인 성공 시 세션에 사용자와 역할을 저장합니다.
        SessionUser user = authService.login(request.username(), request.password());
        session.setAttribute(AuthSession.LOGIN_USER, user);
        return ResponseEntity.ok(toResponse(user));
    }

    @PostMapping("/api/auth/logout")
    @ResponseBody
    public ResponseEntity<Void> logout(HttpSession session) {
        // 세션을 종료해 로그인 유지 상태를 해제합니다.
        session.invalidate();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/auth/me")
    @ResponseBody
    public ResponseEntity<LoginResponse> me(HttpSession session) {
        // 현재 브라우저 세션의 로그인 상태를 확인합니다.
        SessionUser user = (SessionUser) session.getAttribute(AuthSession.LOGIN_USER);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(toResponse(user));
    }

    @GetMapping("/api/auth/social-providers")
    @ResponseBody
    public List<SocialLoginProviderResponse> socialProviders() {
        // 설정 파일에 등록된 소셜 로그인 제공자만 로그인 화면에 노출합니다.
        ClientRegistrationRepository repository = clientRegistrationRepository.getIfAvailable();
        if (!(repository instanceof Iterable<?> registrations)) {
            return List.of();
        }

        List<SocialLoginProviderResponse> providers = new ArrayList<>();
        for (Object registration : registrations) {
            ClientRegistration clientRegistration = (ClientRegistration) registration;
            providers.add(new SocialLoginProviderResponse(
                    clientRegistration.getRegistrationId(),
                    resolveProviderName(clientRegistration),
                    "/oauth2/authorization/" + clientRegistration.getRegistrationId()));
        }
        return providers;
    }

    private LoginResponse toResponse(SessionUser user) {
        return new LoginResponse(user.username(), user.displayName(), user.role().name(), user.email());
    }

    private String resolveProviderName(ClientRegistration clientRegistration) {
        // 로그인 버튼에 표시할 이름은 provider 설정값을 우선 사용합니다.
        String clientName = clientRegistration.getClientName();
        if (clientName == null || clientName.isBlank()) {
            return clientRegistration.getRegistrationId();
        }
        return clientName;
    }
}
