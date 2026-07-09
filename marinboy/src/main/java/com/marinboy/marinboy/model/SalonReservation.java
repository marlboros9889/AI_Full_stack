package com.marinboy.marinboy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

// 고객 한 명의 예약 정보를 저장하는 엔티티입니다.
@Entity
@Table(name = "salon_reservation")
public class SalonReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이 예약에서 선택한 미용실 시술입니다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private SalonServiceItem service;

    // 예약을 생성한 고객 정보입니다.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private SalonCustomer customer;

    @Column(nullable = false, length = 80)
    private String customerName;

    @Column(nullable = false, length = 120)
    private String customerEmail;

    @Column(nullable = false, length = 30)
    private String customerPhone;

    @Column(nullable = false)
    private LocalDateTime reservationDateTime;

    // 고객이 노쇼 방지 예약 안내에 동의했는지 기록합니다.
    @Column(nullable = false)
    private boolean noShowPolicyAgreed;

    // 예약 업무 흐름에서 현재 예약이 가진 상태입니다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservationStatus status;

    @Column(length = 500)
    private String memo;

    public Long getId() {
        return id;
    }

    public SalonServiceItem getService() {
        return service;
    }

    public void setService(SalonServiceItem service) {
        this.service = service;
    }

    public SalonCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(SalonCustomer customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public boolean isNoShowPolicyAgreed() {
        return noShowPolicyAgreed;
    }

    public void setNoShowPolicyAgreed(boolean noShowPolicyAgreed) {
        this.noShowPolicyAgreed = noShowPolicyAgreed;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
