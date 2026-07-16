package com.marinboy.marinboy.config;

import com.marinboy.marinboy.auth.AuthInterceptor;
import com.marinboy.marinboy.service.ImageStorageService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring MVC 요청 흐름에 세션 인증 검사를 연결합니다.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final ImageStorageService imageStorageService;

    public WebConfig(AuthInterceptor authInterceptor, ImageStorageService imageStorageService) {
        this.authInterceptor = authInterceptor;
        this.imageStorageService = imageStorageService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 관리자가 업로드한 시술 이미지를 브라우저에서 바로 볼 수 있게 공개 경로로 연결합니다.
        registry.addResourceHandler("/uploads/service-images/**")
                .addResourceLocations(imageStorageService.getServiceImageDirectory().toUri().toString());
    }
}
