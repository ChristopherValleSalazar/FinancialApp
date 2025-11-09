package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.UserUpdateEmailDTO;
import com.chrisV.BasicFinancialApp.dto.UserUpdateUsernameDTO;
import com.chrisV.BasicFinancialApp.dto.UserUpdateNameDTO;
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
}
