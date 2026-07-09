package com.marinboy.marinboy.model;

// 예약 진행 과정에서 사용하는 상태값입니다.
public enum ReservationStatus {
    REQUESTED,
    CONFIRMED,
    CANCELED,
    COMPLETED,
    NO_SHOW
}
