package com.snookerbooking.dto;

public class BookingDTO {

    private Long id;
    private Long userId;

    private String bookingDate;
    private String timeSlot;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Long snookerTableId;

    public Long getSnookerTableId() {
        return snookerTableId;
    }

    public void setSnookerTableId(Long snookerTableId) {
        this.snookerTableId = snookerTableId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
