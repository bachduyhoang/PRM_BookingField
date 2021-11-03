package com.example.prm_bookingfield.data.model;

public class CartTimePicker {
     private String timePick;
     private float price;

     public CartTimePicker() {
     }

     public CartTimePicker(String timePick, float price) {
          this.timePick = timePick;
          this.price = price;
     }

     public String getTimePick() {
          return timePick;
     }

     public void setTimePick(String timePick) {
          this.timePick = timePick;
     }

     public float getPrice() {
          return price;
     }

     public void setPrice(float price) {
          this.price = price;
     }
}
