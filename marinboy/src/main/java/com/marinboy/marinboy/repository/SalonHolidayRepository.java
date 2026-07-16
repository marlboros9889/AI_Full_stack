package com.marinboy.marinboy.repository;

import com.marinboy.marinboy.model.SalonHoliday;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

// 원장이 지정한 휴무일을 조회하고 저장하는 Repository입니다.
public interface SalonHolidayRepository extends JpaRepository<SalonHoliday, LocalDate> {

    // 관리 화면에서 가까운 휴무일부터 확인할 수 있도록 날짜순으로 조회합니다.
    List<SalonHoliday> findAllByOrderByHolidayDateAsc();
}
