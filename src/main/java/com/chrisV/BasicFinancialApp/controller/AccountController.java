package com.chrisV.BasicFinancialApp.controller;

import com.chrisV.BasicFinancialApp.dto.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin("http://localhost:5173")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<AccountResponseDTO> addAccountToUser(@PathVariable Long userId, @RequestBody AccountRequestDTO account) {
        AccountResponseDTO dto = accountService.addAccountToUser(userId, account);

        //return account without sensitive info
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
