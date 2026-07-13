package com.marinboy.controller;

import com.marinboy.dto.ReservationDto;
import com.marinboy.dto.ServiceDto;
import com.marinboy.dto.UserDto;
import com.marinboy.security.SecurityConstants;
import com.marinboy.service.SalonReservationService;
import com.marinboy.service.SalonServiceService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 고객의 시술 조회와 예약 흐름을 담당합니다. */
@RestController
public class SalonReservationApiController {
    private final SalonServiceService serviceService;
    private final SalonReservationService reservationService;

    public SalonReservationApiController(
            SalonServiceService serviceService,
            SalonReservationService reservationService) {
        this.serviceService = serviceService;
        this.reservationService = reservationService;
    }

    @GetMapping("/api/services")
    public List<ServiceDto> services() {
        return serviceService.getServices();
    }

    @GetMapping("/api/services/{serviceId}/available-slots")
    public ReservationDto availableSlots(
            @PathVariable Long serviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return reservationService.getAvailableSlots(serviceId, date);
    }

    @PostMapping("/api/reservations")
    public ResponseEntity<Void> createReservation(@RequestBody ReservationDto request) {
        reservationService.createReservation(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/customers/history")
    public List<ReservationDto> customerHistory(@RequestParam String customerPhone) {
        return reservationService.getCustomerHistory(customerPhone);
    }

    @GetMapping("/api/customers/my-reservations")
    public ResponseEntity<List<ReservationDto>> myReservations(HttpSession session) {
        Object loginUser = session.getAttribute(SecurityConstants.LOGIN_USER);
        if (!(loginUser instanceof UserDto user) || user.getPhone() == null || user.getPhone().isBlank()) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(reservationService.getCustomerActiveReservations(user.getPhone()));
    }

    @GetMapping("/api/auth/me")
    public ResponseEntity<UserDto> me(HttpSession session) {
        Object loginUser = session.getAttribute(SecurityConstants.LOGIN_USER);
        return loginUser instanceof UserDto user
                ? ResponseEntity.ok(user)
                : ResponseEntity.noContent().build();
    }

    @PostMapping("/api/auth/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.noContent().build();
    }
}
