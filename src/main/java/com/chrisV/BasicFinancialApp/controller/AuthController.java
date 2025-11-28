package com.chrisV.BasicFinancialApp.controller;

import com.chrisV.BasicFinancialApp.dto.user.UserLoginDTO;
import com.chrisV.BasicFinancialApp.dto.user.UserRequestDTO;
import com.chrisV.BasicFinancialApp.dto.user.UserResponseDTO;
import com.chrisV.BasicFinancialApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> userLogin(@RequestBody UserLoginDTO userLogin) {
        UserResponseDTO user = authService.userLogin(userLogin);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user) {
        UserResponseDTO createUser = authService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }
}
