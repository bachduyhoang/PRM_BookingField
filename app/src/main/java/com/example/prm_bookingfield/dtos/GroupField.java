package com.example.prm_bookingfield.dtos;

import java.io.Serializable;
import java.util.List;

public class GroupField implements Serializable {
    private int GroupFieldId;
    private String Name;
    private String Address;
    private String Description;
    private boolean Status;
    private String ImagePath;
    private String ImageName;
    private String CreateAt;
    private String UserId;
    private List<Field> fields;

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public GroupField() {
    }

    public GroupField(int groupFieldId, String name, String address, String description, boolean status,
                      String imagePath, String imageName, String createAt, String userId,
                      List<Field> fields) {
        GroupFieldId = groupFieldId;
        Name = name;
        Address = address;
        Description = description;
        Status = status;
        ImagePath = imagePath;
        ImageName = imageName;
        CreateAt = createAt;
        UserId = userId;
        this.fields = fields;
    }

    public int getGroupFieldId() {
        return GroupFieldId;
    }

    public void setGroupFieldId(int groupFieldId) {
        GroupFieldId = groupFieldId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
