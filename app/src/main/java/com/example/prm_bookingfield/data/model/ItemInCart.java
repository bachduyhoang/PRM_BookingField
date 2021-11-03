package com.example.prm_bookingfield.data.model;

import java.util.Date;
import java.util.List;

public class ItemInCart {
    private String groupFiledName;
    private String imageUrl;
    private String filedName;
    private int typeField;
    private String address;
    private Date bookDate;
    private List<CartTimePicker> timePicker;
    private float total;

    public ItemInCart() {
    }

    public ItemInCart(String groupFiledName, String imageUrl, String filedName, int typeField, String address, Date bookDate, List<CartTimePicker> timePicker, float total) {
        this.groupFiledName = groupFiledName;
        this.imageUrl = imageUrl;
        this.filedName = filedName;
        this.typeField = typeField;
        this.address = address;
        this.bookDate = bookDate;
        this.timePicker = timePicker;
        this.total = total;
    }

    public int getTypeField() {
        return typeField;
    }

    public void setTypeField(int typeField) {
        this.typeField = typeField;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getGroupFiledName() {
        return groupFiledName;
    }

    public void setGroupFiledName(String groupFiledName) {
        this.groupFiledName = groupFiledName;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public List<CartTimePicker> getTimePicker() {
        return timePicker;
    }

    public void setTimePicker(List<CartTimePicker> timePicker) {
        this.timePicker = timePicker;
    }

    public float getTotal() {
        return total;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getTotalList(){
        float result =0;
        for (int i = 0; i < timePicker.size(); i++) {
            result += timePicker.get(i).getPrice();
        }
        return result;
    }
}
