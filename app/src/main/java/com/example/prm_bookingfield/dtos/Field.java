package com.example.prm_bookingfield.dtos;

import java.util.Date;

public class Field {
    private int FieldID;
    private String FieldName;
    private String Address;
    private int TypeField;
    private String ImagePath;
    private String ImageName;
    private String CreateAt;
    private boolean Status;
    private String DeleteAt;
    private boolean IsHot;
    private int TopHot;
    private int GroupFieldID;
    private String UserID;
    private FieldSchedule schedule;

    public Field(int fieldID, String fieldName, String address, int typeField, String imagePath, String imageName, String createAt, boolean status, String deleteAt, boolean isHot, int topHot, int groupFieldID, String userID, FieldSchedule schedule) {
        FieldID = fieldID;
        FieldName = fieldName;
        Address = address;
        TypeField = typeField;
        ImagePath = imagePath;
        ImageName = imageName;
        CreateAt = createAt;
        Status = status;
        DeleteAt = deleteAt;
        IsHot = isHot;
        TopHot = topHot;
        GroupFieldID = groupFieldID;
        UserID = userID;
        this.schedule = schedule;
    }

    public FieldSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(FieldSchedule schedule) {
        this.schedule = schedule;
    }

    public int getFieldID() {
        return FieldID;
    }

    public void setFieldID(int fieldID) {
        FieldID = fieldID;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getTypeField() {
        return TypeField;
    }

    public void setTypeField(int typeField) {
        TypeField = typeField;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(String createAt) {
        CreateAt = createAt;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        this.Status = status;
    }

    public String getDeleteAt() {
        return DeleteAt;
    }

    public void setDeleteAt(String deleteAt) {
        DeleteAt = deleteAt;
    }

    public boolean isHot() {
        return IsHot;
    }

    public void setHot(boolean hot) {
        IsHot = hot;
    }

    public int getTopHot() {
        return TopHot;
    }

    public void setTopHot(int topHot) {
        TopHot = topHot;
    }

    public int getGroupFieldID() {
        return GroupFieldID;
    }

    public void setGroupFieldID(int groupFieldID) {
        GroupFieldID = groupFieldID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public Field() {
    }
}
