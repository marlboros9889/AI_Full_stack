package com.marinboy.marinboy.repository;

import com.marinboy.marinboy.model.SalonCustomer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// 고객 기본 정보를 조회하고 저장하는 Repository입니다.
public interface SalonCustomerRepository extends JpaRepository<SalonCustomer, Long> {

    // 소셜 로그인 고객을 제공자와 제공자 ID로 찾습니다.
    Optional<SalonCustomer> findBySocialProviderAndSocialId(String socialProvider, String socialId);

    // 일반 예약 고객을 연락처로 찾습니다.
    Optional<SalonCustomer> findByPhone(String phone);

    // 이메일로 이미 저장된 고객인지 확인합니다.
    Optional<SalonCustomer> findByEmail(String email);
}
