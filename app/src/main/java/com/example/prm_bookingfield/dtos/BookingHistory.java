package com.example.prm_bookingfield.dtos;

import java.util.List;

public class BookingHistory {
    private String bookingId;
    private String createdAt;
    private String totalPrice;
    private String userForeignKey;
    private List<BookingDetail> bookingDetails;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
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

    public BookingHistory(String bookingId, String createdAt, String totalPrice, String userForeignKey, List<BookingDetail> bookingDetails) {
        this.bookingId = bookingId;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.userForeignKey = userForeignKey;
        this.bookingDetails = bookingDetails;
    }

    public BookingHistory() {
    }
}
