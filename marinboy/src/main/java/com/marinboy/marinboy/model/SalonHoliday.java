package com.marinboy.marinboy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

// 원장이 지정한 특정 휴무일을 저장하는 엔티티입니다.
@Entity
@Table(name = "salon_holiday")
public class SalonHoliday {

    @Id
    private LocalDate holidayDate;

    // 휴무 사유를 남겨 관리 화면에서 어떤 이유로 쉬는지 확인할 수 있게 합니다.
    @Column(length = 200)
    private String reason;

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
