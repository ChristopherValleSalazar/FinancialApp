package com.chrisV.BasicFinancialApp.controller;

import com.chrisV.BasicFinancialApp.dto.account.*;
import com.chrisV.BasicFinancialApp.dto.transaction.TransactionRequest;
import com.chrisV.BasicFinancialApp.dto.transaction.TransactionResponse;
import com.chrisV.BasicFinancialApp.model.account.AccountType;
import com.chrisV.BasicFinancialApp.model.transaction.TransactionCategory;
import com.chrisV.BasicFinancialApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin("http://localhost:5173")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}/checking")
    public ResponseEntity<AccountResponseDTO> getAccountInfo(@PathVariable Long accountId) {
        AccountResponseDTO dto = accountService.findAccountById(accountId);
//        CheckingAccountResponse checkingDetails = (CheckingAccountResponse) dto.getAccountDetails();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //TODO: improve in the future with a token for secure look up of transaction per user account
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<TransactionResponse>> getAccountWithTransactions(@PathVariable Long accountId) {
        return new ResponseEntity<>(accountService.getAllTransactionsByAccountId(accountId), HttpStatus.OK);
    }

    //TODO: improve in the future with a token for secure look up of transaction per user account
    @PostMapping("/createTransaction")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionDto) {
        return new ResponseEntity<>(accountService.createTransaction(transactionDto), HttpStatus.OK);
    }

    //TODO: validate userId and accountId
    @GetMapping("/{userId}/{accountId}/transactionsPerCategory/{transactionCategory}")
    public ResponseEntity<List<TransactionResponse>> getTransactionByType(
            @PathVariable Long userId,
            @PathVariable Long accountId,
            @PathVariable TransactionCategory transactionCategory) {

        return new ResponseEntity<>(accountService.getAllTransactionPerCategory(userId, accountId, transactionCategory), HttpStatus.OK);
    }

    @GetMapping("/{userId}/allSimpleAccountDisplay")
        public ResponseEntity<List<SimpleAccountDisplayDto>> getSimpleAccountDisplay(@PathVariable Long userId) {
            List<SimpleAccountDisplayDto> simpleAccountDisplayDtos = accountService.getAllSimpleAccountDisplayDtos(userId);
            return new ResponseEntity<>(simpleAccountDisplayDtos, HttpStatus.OK);
        }


    //TODO: change to simpler account entity
    @PostMapping("/{userId}/checking")
    public ResponseEntity<AccountResponseDTO> addAccountToUser(@PathVariable Long userId, @RequestBody AccountRequestDTO account) {
        AccountResponseDTO dto = accountService.addAccountToUser(userId, account);
        //return account without sensitive info
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //TODO: change to simpler account entity
    @GetMapping("{userId}/totalBalance/{accountType}")
    public ResponseEntity<BigDecimal> getTotalBalanceByUserIdAndAccountType(@PathVariable Long userId, @PathVariable AccountType accountType) {
        BigDecimal totalBalance = accountService.getTotalBalanceByUserIdAndAccountType(userId, accountType);
        return new ResponseEntity<>(totalBalance, HttpStatus.OK);
    }


    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable Long accountId) {
        BigDecimal balance = accountService.getAccountBalance(accountId);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    //TODO: change to simpler account entity
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
