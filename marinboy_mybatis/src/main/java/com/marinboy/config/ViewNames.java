package com.marinboy.config;

// 컨트롤러가 반환하는 화면 파일명을 한 곳에서 관리하는 클래스입니다.
public final class ViewNames {

    public static final String CUSTOMER_HOME = "index";
    public static final String CUSTOMER_RESERVATION = "reservation";

    private ViewNames() {
        // 화면명 상수만 제공하는 클래스이므로 객체 생성을 막습니다.
    }
}
