package com.example.prm_bookingfield.dtos;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class CartItemViewRequest implements Serializable {
    private int fieldScheduleId ;
    public String fieldName ;
    public String timeStart ;
    public String timeEnd ;
    public String bookingDate ;
    public float price ;
    public int fieldForeignKey ;

    public CartItemViewRequest() {
    }

    public CartItemViewRequest(int fieldScheduleId, String fieldName, String timeStart, String timeEnd, String bookingDate, float price, int fieldForeignKey) {
        this.fieldScheduleId = fieldScheduleId;
        this.fieldName = fieldName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.bookingDate = bookingDate;
        this.price = price;
        fieldForeignKey = fieldForeignKey;
    }

    public JSONObject getJSONObject(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("fieldScheduleId", fieldScheduleId);
            obj.put("fieldName", fieldName);
            obj.put("timeStart", timeStart);
            obj.put("timeEnd", timeEnd);
            obj.put("bookingDate", bookingDate);
            obj.put("price", price);
            obj.put("fieldForeignKey", fieldForeignKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public int getFieldScheduleId() {
        return fieldScheduleId;
    }

    public void setFieldScheduleId(int fieldScheduleId) {
        this.fieldScheduleId = fieldScheduleId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
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

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getFieldForeignKey() {
        return fieldForeignKey;
    }

    public void setFieldForeignKey(int fieldForeignKey) {
        this.fieldForeignKey = fieldForeignKey;
    }
}
