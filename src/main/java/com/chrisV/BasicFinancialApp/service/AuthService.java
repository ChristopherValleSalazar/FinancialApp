package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.UserLoginDTO;
import com.chrisV.BasicFinancialApp.dto.UserRequestDTO;
import com.chrisV.BasicFinancialApp.dto.UserResponseDTO;
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

    public UserResponseDTO createUser(UserRequestDTO user) {
        if(repo.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); //hash with BCrypt before saving

        User savingUser = UserMapper.toEntityFromRequestDTO(user);
        repo.save(savingUser);
        return UserMapper.fromEntityToResponseDTO(savingUser);
    }

    public UserResponseDTO userLogin(UserLoginDTO userLogin) {
        User existingUser = repo.findByUsername(userLogin.getUsername());
        String passwordToCheck = userLogin.getPassword();

        if(passwordEncoder.matches(passwordToCheck, existingUser.getPassword())) {
            return UserMapper.fromEntityToResponseDTO(existingUser);
        }
        return null;
    }
}
