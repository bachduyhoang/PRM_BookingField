package com.example.prm_bookingfield.dtos;

public class User {

    private String FirstName;
    private String LastName;
    private String DayOfBirth;
    private String Email;
    private String Phone;
    private String Username;
    private String Password;
    private String ConfirmPassword;

    private String JwtToken;

    public User(String token) {
        JwtToken = token;
    }

    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String userName) {
        Username = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDayOfBirth() {
        return DayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        DayOfBirth = dayOfBirth;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public User(String firstName, String lastName, String dayOfBirth, String email, String phone, String userName, String password, String confirmPassword) {
        FirstName = firstName;
        LastName = lastName;
        DayOfBirth = dayOfBirth;
        Email = email;
        Phone = phone;
        Username = userName;
        Password = password;
        ConfirmPassword = confirmPassword;
    }

    public User() {
    }
}

