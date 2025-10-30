package com.chrisV.BasicFinancialApp.controller;

import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        //logic
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        return new ResponseEntity<>(users, HttpStatus.OK);

    }


}
