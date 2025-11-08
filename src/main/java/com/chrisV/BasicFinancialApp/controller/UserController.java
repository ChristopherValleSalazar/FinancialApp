package com.chrisV.BasicFinancialApp.controller;

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
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<User> addAccountToUser(@PathVariable Long userId, @RequestBody Account account) {
        User user = userService.addAccountToUser(userId, account);

        //return updated user with account
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.getUserById(id);

        if(existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //check each field for update



        return null;
    }









}
