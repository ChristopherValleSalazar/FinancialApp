package com.chrisV.BasicFinancialApp.controller;

import com.chrisV.BasicFinancialApp.dto.*;
import com.chrisV.BasicFinancialApp.mapper.UserMapper;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        //logic
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user) {
        UserResponseDTO createUser = userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<User> addAccountToUser(@PathVariable Long userId, @RequestBody Account account) {
        User user = userService.addAccountToUser(userId, account);

        //return updated user with account
        return new ResponseEntity<>(user, HttpStatus.CREATED);
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
