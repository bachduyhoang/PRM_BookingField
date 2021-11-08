package com.example.prm_bookingfield.dtos;

public class ButtonToggle {
    private String time;
    private boolean status;

    public ButtonToggle() {
    }

    public ButtonToggle(String time, boolean status) {
        this.time = time;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
