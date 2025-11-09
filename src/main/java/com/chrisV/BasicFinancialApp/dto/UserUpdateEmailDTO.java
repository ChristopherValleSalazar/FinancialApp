package com.chrisV.BasicFinancialApp.dto;

public class UserUpdateEmailDTO {
    private String email;

    public UserUpdateEmailDTO() {}

    public UserUpdateEmailDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

