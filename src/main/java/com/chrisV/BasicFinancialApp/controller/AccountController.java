package com.chrisV.BasicFinancialApp.controller;

import com.chrisV.BasicFinancialApp.dto.account.*;
import com.chrisV.BasicFinancialApp.dto.transaction.TransactionRequest;
import com.chrisV.BasicFinancialApp.dto.transaction.TransactionResponse;
import com.chrisV.BasicFinancialApp.service.AccountService;
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
        AccountResponseDTO dto = accountService.findAccountById(accountId).orElseThrow(() -> new RuntimeException("Account with id " + accountId + " is not a checking account"));
        CheckingAccountResponse checkingDetails = (CheckingAccountResponse) dto.getAccountDetails();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //TODO: improve in the future with a token for secure look up of transaction per user account
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionResponse>> getAccountWithTransactions(@PathVariable Long accountId) {
        return new ResponseEntity<>(accountService.getAllTransactionsByAccountId(accountId), HttpStatus.OK);
    }

    @PostMapping("/createTransaction")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionDto) {
        return new ResponseEntity<>(accountService.createTransaction(transactionDto), HttpStatus.OK);
    }

    // TODO: restrict Balance to $0 during creation for better handling with expense and income endpoints
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
        AccountResponseDTO accountResponseDTO = accountService.deleteAccount(userId, accountId);
        return new ResponseEntity<>(accountResponseDTO, HttpStatus.OK);
    }

    @PatchMapping("updateAccount/{accountId}")
    public ResponseEntity<AccountResponseDTO> updateAccount( @PathVariable Long accountId, @RequestBody AccountUpdateRequestDTO accountRequestDTO) {
        AccountResponseDTO updatedAccount = accountService.updateAccount(accountId, accountRequestDTO);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }






}
