package com.chrisV.BasicFinancialApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

// Simple user with no sensitive info
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;


    public UserResponseDTO() {}
    public UserResponseDTO(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
