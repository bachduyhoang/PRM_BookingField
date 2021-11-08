package com.example.prm_bookingfield.dtos;

import java.util.List;

public class BookingHistory {
    private int bookingId;
    private String createdAt;
    private String originPrice;
    private String totalPrice;
    private String status;
    private String userForeignKey;
    private List<BookingDetail> bookingDetails;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserForeignKey() {
        return userForeignKey;
    }

    public void setUserForeignKey(String userForeignKey) {
        this.userForeignKey = userForeignKey;
    }

    public List<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public BookingHistory(int bookingId, String createdAt, String originPrice, String totalPrice, String status, String userForeignKey, List<BookingDetail> bookingDetails) {
        this.bookingId = bookingId;
        this.createdAt = createdAt;
        this.originPrice = originPrice;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userForeignKey = userForeignKey;
        this.bookingDetails = bookingDetails;
    }

    public BookingHistory() {
    }
}
