package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.*;
import com.chrisV.BasicFinancialApp.model.User;

public class UserMapper {

    public static UserUpdateNameDTO fromEntityNameDTO(User user) {

        UserUpdateNameDTO dto = new UserUpdateNameDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    public static UserUpdateUsernameDTO fromEntityUsernameDTO(User user) {
        return new UserUpdateUsernameDTO(user.getUsername());
    }

    public static UserUpdateEmailDTO fromEntityEmailDTO(User user) {
        return new UserUpdateEmailDTO(user.getEmail());
    }

    public static User toEntityResponseDTO(UserResponseDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserResponseDTO fromEntityToResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }


    public static User toEntityFromRequestDTO(UserRequestDTO user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        return newUser;
    }

    public static UserRequestDTO fromEntityToRequestDTO(User user) {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }




}
