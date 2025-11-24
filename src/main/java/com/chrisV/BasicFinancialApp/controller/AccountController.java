package com.chrisV.BasicFinancialApp.controller;

import com.chrisV.BasicFinancialApp.dto.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    UserService userService;

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<AccountResponseDTO> addAccountToUser(@PathVariable Long userId, @RequestBody AccountRequestDTO account) {
        AccountResponseDTO dto = userService.addAccountToUser(userId, account);

        //return account without sensitive info
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
