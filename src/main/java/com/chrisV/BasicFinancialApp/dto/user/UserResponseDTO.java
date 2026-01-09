package com.chrisV.BasicFinancialApp.dto.user;

import lombok.*;

@Data
@NoArgsConstructor

// Simple user with no sensitive info
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
