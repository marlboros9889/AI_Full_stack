package com.marinboy.marinboy.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// 세션 로그인 여부와 역할별 접근 권한을 검사합니다.
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        if (isPublicPath(path)) {
            return true;
        }

        HttpSession session = request.getSession(false);
        SessionUser user = session == null ? null : (SessionUser) session.getAttribute(AuthSession.LOGIN_USER);
        if (user == null) {
            reject(request, response, HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
            return false;
        }

        if (requiresAdmin(request) && user.role() != AuthRole.ADMIN) {
            reject(request, response, HttpServletResponse.SC_FORBIDDEN, "관리자 권한이 필요합니다.");
            return false;
        }

        return true;
    }

    private boolean isPublicPath(String path) {
        // 로그인과 기본 상태 확인 API는 세션 없이 접근할 수 있습니다.
        return path.equals("/login")
                || path.equals("/api/auth/login")
                || path.equals("/api/auth/me")
                || path.equals("/api/auth/social-providers")
                || path.startsWith("/oauth2/")
                || path.startsWith("/login/oauth2/")
                || path.startsWith("/uploads/")
                || path.equals("/api/health")
                || path.equals("/api/db-time")
                || path.startsWith("/h2-console");
    }

    private boolean requiresAdmin(HttpServletRequest request) {
        // 관리자 화면과 운영 변경 API는 원장 계정만 접근할 수 있습니다.
        String path = request.getRequestURI();
        String method = request.getMethod();
        return path.equals("/admin")
                || (path.equals("/api/services") && "POST".equals(method))
                || (path.equals("/api/service-images") && "POST".equals(method))
                || path.startsWith("/api/holidays")
                || path.startsWith("/api/reservations/reminder-targets")
                || path.matches("^/api/reservations/\\d+/status$")
                || (path.matches("^/api/services/\\d+$") && ("PUT".equals(method) || "DELETE".equals(method)))
                || path.matches("^/api/services/\\d+/price$");
    }

    private void reject(
            HttpServletRequest request,
            HttpServletResponse response,
            int status,
            String message) throws Exception {
        if (request.getRequestURI().startsWith("/api/")) {
            response.setStatus(status);
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{\"message\":\"" + message + "\"}");
            return;
        }

        response.sendRedirect("/login");
    }
}
