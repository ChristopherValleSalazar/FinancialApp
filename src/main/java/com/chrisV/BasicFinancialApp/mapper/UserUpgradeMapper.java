package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.user.*;
import com.chrisV.BasicFinancialApp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserUpgradeMapper {

    UserUpgradeMapper INSTANCE = Mappers.getMapper(UserUpgradeMapper.class); //singleton instance

    //implementing mapStruct preventing boilerplate code
    UserUpdateNameDTO userToUserUpdateNameDTO(User user); //TODO: In UserMapper
    UserUpdateUsernameDTO userToUserUpdateUsernameDTO(User user); //TODO: In UserMapper
    UserUpdateEmailDTO userToUserUpdateEmailDTO(User user); //TODO: In UserMapper
    User userResponseDTOToUser(UserResponseDTO dto); //TODO: In UserMapper
    UserResponseDTO userToUserResponseDTO(User user); //TODO: In UserMapper
    User userRequestDTOToUser(UserRequestDTO dto); //TODO: In UserMapper
    UserRequestDTO userToUserRequestDTO(User user); //TODO: In UserMapper


    //UserLoginDTO userToUserLoginDTO(User user); //TODO: In AuthService


//TODO: Need to implement these methods into the userService methods, also start learning how to implement MapStruct into the account mapping with nested objects.


}
