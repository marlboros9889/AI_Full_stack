package com.marinboy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDto {
    private Long id;
    private Long serviceId;
    private String serviceName;
    private String serviceCategory;
    private Integer durationMinutes;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private LocalDateTime reservationDateTime;
    private Boolean noShowPolicyAgreed;
    private String status;
    private String memo;
    private List<LocalDateTime> availableSlots;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getServiceId() { return serviceId; }
    public void setServiceId(Long serviceId) { this.serviceId = serviceId; }
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public String getServiceCategory() { return serviceCategory; }
    public void setServiceCategory(String serviceCategory) { this.serviceCategory = serviceCategory; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public LocalDateTime getReservationDateTime() { return reservationDateTime; }
    public void setReservationDateTime(LocalDateTime reservationDateTime) { this.reservationDateTime = reservationDateTime; }
    public Boolean getNoShowPolicyAgreed() { return noShowPolicyAgreed; }
    public void setNoShowPolicyAgreed(Boolean noShowPolicyAgreed) { this.noShowPolicyAgreed = noShowPolicyAgreed; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }
    public List<LocalDateTime> getAvailableSlots() { return availableSlots; }
    public void setAvailableSlots(List<LocalDateTime> availableSlots) { this.availableSlots = availableSlots; }
}
