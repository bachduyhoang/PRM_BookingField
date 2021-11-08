package com.example.prm_bookingfield.dtos;

public class FieldSchedule {
    private String TimeStart;
    private String TimeEnd;
    private String Status;
    private String Price;
    private String OriginPrice;
    private String FieldForeignKey;

    public FieldSchedule(String timeStart, String timeEnd, String status, String price, String originPrice, String fieldForeignKey) {
        TimeStart = timeStart;
        TimeEnd = timeEnd;
        Status = status;
        Price = price;
        OriginPrice = originPrice;
        FieldForeignKey = fieldForeignKey;
    }

    public FieldSchedule() {
    }

    public String getTimeStart() {
        return TimeStart;
    }

    public void setTimeStart(String timeStart) {
        TimeStart = timeStart;
    }

    public String getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        TimeEnd = timeEnd;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getOriginPrice() {
        return OriginPrice;
    }

    public void setOriginPrice(String originPrice) {
        OriginPrice = originPrice;
    }

    public String getFieldForeignKey() {
        return FieldForeignKey;
    }

    public void setFieldForeignKey(String fieldForeignKey) {
        FieldForeignKey = fieldForeignKey;
    }
}
