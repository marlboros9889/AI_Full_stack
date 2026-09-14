package com.thejoa703.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.thejoa703.entity.AppUser;
import com.thejoa703.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

// 소셜 계정 정보를 조회하고 최초 로그인 사용자를 자동으로 등록한다.
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final AppUserRepository appUserRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> response = provider.equals("naver") ? getMap(attributes, "response") : attributes;

        String providerId = String.valueOf(response.get(provider.equals("google") ? "sub" : "id"));
        String email = value(response, "email", provider + "_" + providerId + "@social.local");
        String nickname = value(response, provider.equals("google") ? "name" : "nickname", provider + " 사용자");

        appUserRepository.findByProviderAndProviderId(provider, providerId)
            .orElseGet(() -> appUserRepository.save(AppUser.builder()
                .email(email).password(UUID.randomUUID().toString()).nickname(nickname)
                .role("ROLE_USER").provider(provider).providerId(providerId).deleted(false).build()));
        return oAuth2User;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getMap(Map<String, Object> attributes, String key) {
        return (Map<String, Object>) attributes.get(key);
    }

    private String value(Map<String, Object> attributes, String key, String defaultValue) {
        Object value = attributes.get(key);
        return value == null || value.toString().isBlank() ? defaultValue : value.toString();
    }
}
