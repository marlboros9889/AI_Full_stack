package com.marinboy.marinboy.repository;

import com.marinboy.marinboy.model.SalonServiceItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// 고정된 미용실 시술 항목을 조회하는 Repository입니다.
public interface SalonServiceItemRepository extends JpaRepository<SalonServiceItem, Long> {

    Optional<SalonServiceItem> findByTopRank(Integer topRank);
}
