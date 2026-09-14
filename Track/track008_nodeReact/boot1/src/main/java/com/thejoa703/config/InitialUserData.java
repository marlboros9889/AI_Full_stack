package com.thejoa703.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.thejoa703.entity.AppUser;
import com.thejoa703.repository.AppUserRepository;

// 애플리케이션 최초 실행 시 학습용 관리자와 테스트 계정을 만든다.
@Configuration
public class InitialUserData {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner createInitialUsers(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            createUserIfAbsent(appUserRepository, passwordEncoder, "admin@thejoa703.com", "관리자", "ROLE_ADMIN", "admin1234");
            createUserIfAbsent(appUserRepository, passwordEncoder, "test@thejoa703.com", "테스트사용자", "ROLE_USER", "test1234");
        };
    }

    private void createUserIfAbsent(AppUserRepository repository, PasswordEncoder passwordEncoder, String email, String nickname, String role, String rawPassword) {
        if (repository.findByEmail(email).isPresent()) return;
        repository.save(AppUser.builder().email(email).password(passwordEncoder.encode(rawPassword)).nickname(nickname).role(role).provider("local").providerId("local").deleted(false).build());
    }
}
