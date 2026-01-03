package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.user.*;
import com.chrisV.BasicFinancialApp.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") //TODO: make all places where this is use into a spring bean injection type implementation
public interface UserMapper {

    //implementing mapStruct preventing boilerplate code
    UserUpdateNameDTO userToUserUpdateNameDTO(User user); //TODO: In UserMapper
    UserUpdateUsernameDTO userToUserUpdateUsernameDTO(User user); //TODO: In UserMapper
    UserUpdateEmailDTO userToUserUpdateEmailDTO(User user); //TODO: In UserMapper

    User userResponseDTOToUser(UserResponseDTO dto); //TODO: In UserMapper
    UserResponseDTO userToUserResponseDTO(User user); //TODO: In UserMapper
    User userRequestDTOToUser(UserRequestDTO dto); //TODO: In UserMapper
    UserRequestDTO userToUserRequestDTO(User user); //TODO: In UserMapper


    //UserLoginDTO userToUserLoginDTO(User user); //TODO: In AuthService


}
