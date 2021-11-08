package com.example.prm_bookingfield.dtos;

public class BookingDetail {

    private int bookingDetailId;
    private String timeStart;
    private String timeEnd;
    private String price;
    private String status;
    private String bookingForeignKey;
    private String fieldScheduleForeignKey;
    private String fieldForeignKey;

    public int getBookingDetailId() {
        return bookingDetailId;
    }

    public void setBookingDetailId(int bookingDetailId) {
        this.bookingDetailId = bookingDetailId;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookingForeignKey() {
        return bookingForeignKey;
    }

    public void setBookingForeignKey(String bookingForeignKey) {
        this.bookingForeignKey = bookingForeignKey;
    }

    public String getFieldScheduleForeignKey() {
        return fieldScheduleForeignKey;
    }

    public void setFieldScheduleForeignKey(String fieldScheduleForeignKey) {
        this.fieldScheduleForeignKey = fieldScheduleForeignKey;
    }

    public String getFieldForeignKey() {
        return fieldForeignKey;
    }

    public void setFieldForeignKey(String fieldForeignKey) {
        this.fieldForeignKey = fieldForeignKey;
    }

    public BookingDetail() {
    }

    public BookingDetail(int bookingDetailId, String timeStart, String timeEnd, String price, String status, String bookingForeignKey, String fieldScheduleForeignKey, String fieldForeignKey) {
        this.bookingDetailId = bookingDetailId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.price = price;
        this.status = status;
        this.bookingForeignKey = bookingForeignKey;
        this.fieldScheduleForeignKey = fieldScheduleForeignKey;
        this.fieldForeignKey = fieldForeignKey;
    }
}
