package com.marinboy.marinboy.config;

import com.marinboy.marinboy.auth.OAuth2LoginSuccessHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

// Spring Security OAuth2 로그인을 기존 세션 인증 흐름과 함께 사용하도록 설정합니다.
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            ObjectProvider<ClientRegistrationRepository> clientRegistrationRepository,
            OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler) throws Exception {
        // 기존 AuthInterceptor가 화면/API 권한을 담당하므로 Security는 OAuth2 로그인 진입만 열어둡니다.
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());

        if (clientRegistrationRepository.getIfAvailable() != null) {
            // application-oauth.yml에 소셜 키가 설정된 경우에만 실제 OAuth2 로그인 흐름을 활성화합니다.
            http.oauth2Login(oauth2 -> oauth2
                    .loginPage("/login")
                    .successHandler(oAuth2LoginSuccessHandler));
        }

        return http.build();
    }
}
