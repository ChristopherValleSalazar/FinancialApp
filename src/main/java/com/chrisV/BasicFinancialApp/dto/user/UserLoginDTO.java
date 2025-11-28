package com.chrisV.BasicFinancialApp.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    private Long id;
    private String password;
    private String username;

    public UserLoginDTO() {}
    public UserLoginDTO(Long id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }
}
