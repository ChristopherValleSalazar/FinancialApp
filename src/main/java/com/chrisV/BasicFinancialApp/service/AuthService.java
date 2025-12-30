package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.user.UserLoginDTO;
import com.chrisV.BasicFinancialApp.dto.user.UserRequestDTO;
import com.chrisV.BasicFinancialApp.dto.user.UserResponseDTO;
import com.chrisV.BasicFinancialApp.mapper.UserMapper;
import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo repo;

    @Autowired
    private UserMapper mapper;


//    private final UserMapper mapper = UserMapper.INSTANCE;

    public UserResponseDTO createUser(UserRequestDTO user) {
        if(repo.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); //hash with BCrypt before saving
        User savingUser = mapper.userRequestDTOToUser(user);

        repo.save(savingUser);
        return mapper.userToUserResponseDTO(savingUser);
    }

    public UserResponseDTO userLogin(UserLoginDTO userLogin) {
        User existingUser = repo.findByUsername(userLogin.getUsername());
        String passwordToCheck = userLogin.getPassword();

        if(!passwordEncoder.matches(passwordToCheck, existingUser.getPassword())) {
            return null; //invalid password
        }
        return mapper.userToUserResponseDTO(existingUser);
    }
}
