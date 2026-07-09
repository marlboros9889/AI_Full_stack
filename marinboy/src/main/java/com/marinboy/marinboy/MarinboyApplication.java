package com.marinboy.marinboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 1인 미용실 예약관리 서비스를 시작하는 진입점입니다.
@SpringBootApplication
public class MarinboyApplication {

    public static void main(String[] args) {
        // Spring Boot를 실행하고 전체 애플리케이션 설정을 로드합니다.
        SpringApplication.run(MarinboyApplication.class, args);
    }
}
