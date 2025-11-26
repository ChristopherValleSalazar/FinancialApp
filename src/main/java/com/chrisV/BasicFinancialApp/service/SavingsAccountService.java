package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.SavingsAccountDetails;
import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.repository.AccountRepo;
import com.chrisV.BasicFinancialApp.repository.SavingsAccountRepo;
import com.chrisV.BasicFinancialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SavingsAccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private SavingsAccountRepo savingsRepo;

    @Autowired
    private UserRepo userRepo;

    public Account createSavingsAccount(Long userId, String name, BigDecimal apy) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Account a = new Account();
        a.setUser(user);
        a.setAccountType("SAVINGS");
        a.setBalance(BigDecimal.ZERO);
        a.setCurrency("USD");

        Account saved = accountRepo.save(a);

        SavingsAccountDetails details = new SavingsAccountDetails();
        details.setAccount(saved);
        details.setApy(apy);
        details.setLastInterestApplied(LocalDate.now());

        SavingsAccountDetails savedDetails = savingsRepo.save(details);
        return saved;
    }




}
