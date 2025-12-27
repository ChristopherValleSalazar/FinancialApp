package com.chrisV.BasicFinancialApp.dto.user;

public class UserUpdateUsernameDTO {
    private String username;

    public UserUpdateUsernameDTO() {}

    public UserUpdateUsernameDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

