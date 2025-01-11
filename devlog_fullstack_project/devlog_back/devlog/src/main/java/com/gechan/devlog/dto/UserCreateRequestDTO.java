package com.gechan.devlog.dto;

public class UserCreateRequestDTO {
    private String email;
    private String pw;
    private String name;
    private String phone;

    public UserCreateRequestDTO() {
    }

    public UserCreateRequestDTO(String email, String pw, String name, String phone) {
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
