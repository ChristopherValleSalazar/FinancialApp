package com.chrisV.BasicFinancialApp.dto.user;

import lombok.*;

@Data
@NoArgsConstructor
public class UserLoginDTO {
    private String password;
    private String username;
}
