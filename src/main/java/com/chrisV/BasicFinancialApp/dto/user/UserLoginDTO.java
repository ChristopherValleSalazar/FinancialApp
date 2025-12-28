package com.chrisV.BasicFinancialApp.dto.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    private String password;
    private String username;
}
