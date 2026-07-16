package com.marinboy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring MVC 공통 설정을 담당하는 클래스입니다.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 현재 단계에서는 기본 MVC 설정을 그대로 사용합니다.
    // 이후 이미지 업로드 경로, 인터셉터, CORS 같은 웹 설정이 필요하면 이 클래스에 추가합니다.
}
