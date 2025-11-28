package com.chrisV.BasicFinancialApp.controller;

import com.chrisV.BasicFinancialApp.dto.account.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.account.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.dto.account.CheckingAccountResponse;
import com.chrisV.BasicFinancialApp.dto.account.CreateSavingsDTO;
import com.chrisV.BasicFinancialApp.mapper.AccountMapper;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.SavingsAccountDetails;
import com.chrisV.BasicFinancialApp.repository.AccountRepo;
import com.chrisV.BasicFinancialApp.repository.SavingsAccountRepo;
import com.chrisV.BasicFinancialApp.service.AccountService;
import com.chrisV.BasicFinancialApp.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin("http://localhost:5173")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}/checking")
    public ResponseEntity<AccountResponseDTO> getAccountInfo(@PathVariable Long accountId) {
        AccountResponseDTO dto = accountService.findAccountById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        CheckingAccountResponse checkingDetails = (CheckingAccountResponse) dto.getAccountDetails();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/{userId}/checking")
    public ResponseEntity<AccountResponseDTO> addAccountToUser(@PathVariable Long userId, @RequestBody AccountRequestDTO account) {
        AccountResponseDTO dto = accountService.addCheckingAccountToUser(userId, account);
        //return account without sensitive info
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("{userId}/AllChecking")
    public ResponseEntity<List<AccountResponseDTO>> getAllCheckingAccountsByUserId(@PathVariable Long userId) {
        List<AccountResponseDTO> accounts = accountService.getAllCheckingAccountsByUserId(userId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @DeleteMapping("{userId}/deleteAccount/{accountId}")
    public ResponseEntity<AccountResponseDTO> deleteAccount(@PathVariable Long userId,@PathVariable Long accountId) {
        AccountResponseDTO accountResponseDTO=  accountService.deleteAccount(userId, accountId);
        return new ResponseEntity<>(accountResponseDTO, HttpStatus.OK);
    }

//    @PostMapping("/savings")
//    public ResponseEntity<AccountResponseDTO> createSavingsAccount(@RequestBody CreateSavingsDTO accountRequestDTO) {
//
//        Account account = repo.findById(1L).orElseThrow(() -> new RuntimeException("Account not found"));
//
//        SavingsAccountDetails savingsDetails = new SavingsAccountDetails();
//        savingsDetails.setAccount(account);
//        savingsDetails.setApy(accountRequestDTO.getApy());
//
//        savingsAccountRepo.save(savingsDetails);
//
//        return new ResponseEntity<>(AccountMapper.fromEntityToResponseDTO(account), HttpStatus.CREATED);
//    }
}
