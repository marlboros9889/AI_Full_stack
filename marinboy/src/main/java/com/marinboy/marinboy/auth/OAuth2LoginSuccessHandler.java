package com.marinboy.marinboy.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

// 소셜 로그인 성공 정보를 우리 서비스의 세션 사용자 정보로 변환합니다.
@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = token.getPrincipal();

        String registrationId = token.getAuthorizedClientRegistrationId();
        String displayName = resolveDisplayName(registrationId, oAuth2User.getAttributes());
        String email = resolveEmail(registrationId, oAuth2User.getAttributes());
        String socialId = resolveSocialId(registrationId, oAuth2User);
        String username = "social:%s:%s".formatted(registrationId, socialId);

        // 소셜 로그인 사용자는 고객 권한으로 시작하고, 관리자 권한은 기존 관리자 계정으로 분리합니다.
        request.getSession().setAttribute(
                AuthSession.LOGIN_USER,
                new SessionUser(username, displayName, AuthRole.CUSTOMER, email, registrationId, socialId));
        response.sendRedirect("/");
    }

    private String resolveSocialId(String registrationId, OAuth2User oAuth2User) {
        // 제공자별 고유 ID를 저장해야 재방문 고객 이력과 소셜 계정이 안정적으로 연결됩니다.
        Map<String, Object> attributes = oAuth2User.getAttributes();
        if ("naver".equals(registrationId) && attributes.get("response") instanceof Map<?, ?> response) {
            Object id = response.get("id");
            return id == null ? oAuth2User.getName() : String.valueOf(id);
        }

        Object id = attributes.get("id");
        if (id != null) {
            return String.valueOf(id);
        }

        Object sub = attributes.get("sub");
        if (sub != null) {
            return String.valueOf(sub);
        }

        return oAuth2User.getName();
    }

    private String resolveDisplayName(String registrationId, Map<String, Object> attributes) {
        // 제공자별 응답 구조가 달라서 고객 화면에 표시할 이름을 안전하게 꺼냅니다.
        if ("naver".equals(registrationId) && attributes.get("response") instanceof Map<?, ?> response) {
            Object name = response.get("name");
            return name == null ? "네이버 고객" : String.valueOf(name);
        }

        if ("kakao".equals(registrationId) && attributes.get("properties") instanceof Map<?, ?> properties) {
            Object nickname = properties.get("nickname");
            return nickname == null ? "카카오 고객" : String.valueOf(nickname);
        }

        Object name = attributes.get("name");
        if (name != null) {
            return String.valueOf(name);
        }

        Object email = attributes.get("email");
        if (email != null) {
            return String.valueOf(email);
        }

        return "소셜 로그인 고객";
    }

    private String resolveEmail(String registrationId, Map<String, Object> attributes) {
        // 소셜 제공자가 이메일을 내려주면 예약 폼 자동 입력에 사용합니다.
        if ("naver".equals(registrationId) && attributes.get("response") instanceof Map<?, ?> response) {
            Object email = response.get("email");
            return email == null ? null : String.valueOf(email);
        }

        if ("kakao".equals(registrationId) && attributes.get("kakao_account") instanceof Map<?, ?> account) {
            Object email = account.get("email");
            return email == null ? null : String.valueOf(email);
        }

        Object email = attributes.get("email");
        return email == null ? null : String.valueOf(email);
    }
}
