package com.chrisV.BasicFinancialApp.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateNameDTO {
    private String firstName;
    private String lastName;
}
