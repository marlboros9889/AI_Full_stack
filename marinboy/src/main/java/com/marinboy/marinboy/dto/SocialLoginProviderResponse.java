package com.marinboy.marinboy.dto;

// 로그인 화면에서 사용할 소셜 로그인 제공자 정보를 담습니다.
public record SocialLoginProviderResponse(
        String registrationId,
        String displayName,
        String authorizationUrl) {
}
