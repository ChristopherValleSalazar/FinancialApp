package com.chrisV.BasicFinancialApp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateNameDTO {
    private String firstName;
    private String lastName;
}
