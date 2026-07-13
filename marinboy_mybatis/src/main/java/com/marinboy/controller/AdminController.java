package com.marinboy.controller;

import com.marinboy.dto.UserDto;
import com.marinboy.security.SecurityConstants;
import com.marinboy.service.SalonReservationService;
import com.marinboy.service.SalonServiceService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AdminController {
    @GetMapping("/admin") public String admin(HttpSession session) { requireAdmin(session); return "admin"; }
    static void requireAdmin(HttpSession session) {
        Object user = session.getAttribute(SecurityConstants.LOGIN_USER);
        if (!(user instanceof UserDto current) || !SecurityConstants.ROLE_ADMIN.equals(current.getRole())) throw new IllegalArgumentException("관리자 로그인이 필요합니다.");
    }
    @RestController
    static class AdminApi {
        private final SalonReservationService service;
        private final SalonServiceService salonService;
        AdminApi(SalonReservationService service, SalonServiceService salonService) { this.service = service; this.salonService = salonService; }
        @GetMapping("/api/admin/reservations") Object reservations(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, HttpSession s) { requireAdmin(s); return Map.of("items", service.getReservationsPage(page, size), "total", service.countReservations(), "page", page, "size", size); }
        @GetMapping("/api/admin/reservations/{id}") Object reservation(@org.springframework.web.bind.annotation.PathVariable Long id, HttpSession s) { requireAdmin(s); return service.getReservation(id); }
        @GetMapping("/api/admin/reservations/reminder-targets") Object reminderTargets(HttpSession s) { requireAdmin(s); return service.getReminderTargets(); }
        @PatchMapping("/api/admin/reservations/{id}/status") ResponseEntity<Void> status(@org.springframework.web.bind.annotation.PathVariable Long id, @RequestParam String status, HttpSession s) { requireAdmin(s); service.updateReservationStatus(id, status); return ResponseEntity.noContent().build(); }
        @PatchMapping("/api/admin/reservations/{id}/reject") ResponseEntity<Void> reject(@org.springframework.web.bind.annotation.PathVariable Long id, @RequestParam String reason, HttpSession s) { requireAdmin(s); service.rejectReservation(id, reason); return ResponseEntity.noContent().build(); }
        @GetMapping("/api/admin/holidays") Object holidays(HttpSession s) { requireAdmin(s); return service.getHolidays(); }
        @PostMapping("/api/admin/holidays") ResponseEntity<Void> save(@RequestBody Map<String,String> body, HttpSession s) { requireAdmin(s); service.saveHoliday(LocalDate.parse(body.get("holidayDate")), body.get("reason")); return ResponseEntity.noContent().build(); }
        @DeleteMapping("/api/admin/holidays") ResponseEntity<Void> delete(@RequestParam LocalDate holidayDate, HttpSession s) { requireAdmin(s); service.deleteHoliday(holidayDate); return ResponseEntity.noContent().build(); }
        @GetMapping("/api/admin/services") Object services(HttpSession s) { requireAdmin(s); return salonService.getServices(); }
        @PostMapping("/api/admin/services") ResponseEntity<Void> createService(@RequestBody Map<String,String> body, HttpSession s) { requireAdmin(s); salonService.saveService(null, body.get("name"), body.get("category"), Integer.parseInt(body.get("durationMinutes")), Integer.parseInt(body.get("price")), body.get("description")); return ResponseEntity.noContent().build(); }
        @PatchMapping("/api/admin/services/{id}") ResponseEntity<Void> updateService(@org.springframework.web.bind.annotation.PathVariable Long id, @RequestBody Map<String,String> body, HttpSession s) { requireAdmin(s); salonService.saveService(id, body.get("name"), body.get("category"), Integer.parseInt(body.get("durationMinutes")), Integer.parseInt(body.get("price")), body.get("description")); return ResponseEntity.noContent().build(); }
        @DeleteMapping("/api/admin/services/{id}") ResponseEntity<Void> deleteService(@org.springframework.web.bind.annotation.PathVariable Long id, HttpSession s) { requireAdmin(s); salonService.deleteService(id); return ResponseEntity.noContent().build(); }
    }
}
