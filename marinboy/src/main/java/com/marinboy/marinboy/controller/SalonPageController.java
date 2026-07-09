package com.marinboy.marinboy.controller;

import com.marinboy.marinboy.service.SalonReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 브라우저에 표시되는 고객 화면과 관리자 화면을 렌더링합니다.
@Controller
public class SalonPageController {

    private final SalonReservationService salonReservationService;

    public SalonPageController(SalonReservationService salonReservationService) {
        this.salonReservationService = salonReservationService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // 고객 예약 화면 상단에 표시할 프로젝트 요약 정보를 전달합니다.
        model.addAttribute("projectName", "마린 헤어 스튜디오");
        model.addAttribute("headline", "1인 미용실 예약");
        model.addAttribute("description", "원하는 시술과 날짜를 선택해 예약 가능한 시간을 확인하고 예약을 요청할 수 있습니다.");
        model.addAttribute("serviceCount", salonReservationService.getServiceCatalog().size());
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        // 원장 전용 관리 화면에 필요한 요약 정보를 전달합니다.
        model.addAttribute("projectName", "마린 헤어 스튜디오 관리자");
        model.addAttribute("description", "예약 상태, 시술 가격, 특정 휴무일을 관리하는 원장 전용 화면입니다.");
        model.addAttribute("reservationCount", salonReservationService.getReservations().size());
        model.addAttribute("serviceCount", salonReservationService.getServiceCatalog().size());
        model.addAttribute("holidayCount", salonReservationService.getHolidays().size());
        return "admin";
    }
}
