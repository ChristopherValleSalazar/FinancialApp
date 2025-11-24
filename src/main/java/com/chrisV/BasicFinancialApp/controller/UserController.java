package com.chrisV.BasicFinancialApp.controller;

import com.chrisV.BasicFinancialApp.dto.*;
import com.chrisV.BasicFinancialApp.mapper.UserMapper;
import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        //logic
        List<UserResponseDTO> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> userLogin(@RequestBody UserLoginDTO userLogin) {
        UserResponseDTO user = userService.userLogin(userLogin);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user) {
        UserResponseDTO createUser = userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<AccountResponseDTO> addAccountToUser(@PathVariable Long userId, @RequestBody AccountRequestDTO account) {
        AccountResponseDTO dto = userService.addAccountToUser(userId, account);

        //return account without sensitive info
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<UserUpdateNameDTO> updateUserNames(@PathVariable Long id, @RequestBody UserUpdateNameDTO userUpdate) {
        //check each field and update except id and accounts
        User updatedUser = userService.updateOnlyNameUser(userUpdate, id);

        return new ResponseEntity<>(UserMapper.fromEntityNameDTO(updatedUser), HttpStatus.OK);
    }

    @PatchMapping("/update-username/{id}")
    public ResponseEntity<UserUpdateUsernameDTO> updateUsername(
            @PathVariable Long id,
            @RequestBody UserUpdateUsernameDTO userUpdate) {

        User updatedUser = userService.updateOnlyUsernameUser(userUpdate, id);

        return new ResponseEntity<>(UserMapper.fromEntityUsernameDTO(updatedUser), HttpStatus.OK);
    }

    @PatchMapping("/update-email/{id}")
    public ResponseEntity<UserUpdateEmailDTO> updateEmail(
            @PathVariable Long id,
            @RequestBody UserUpdateEmailDTO userUpdate) {

        User updatedUser = userService.updateOnlyEmailUser(userUpdate, id);

        return new ResponseEntity<>(UserMapper.fromEntityEmailDTO(updatedUser), HttpStatus.OK);
    }







}
