package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.CheckingAccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.dto.CheckingAccountResponseDTO;
import com.chrisV.BasicFinancialApp.mapper.AccountMapper;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.CheckingAccountDetails;
import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.repository.AccountRepo;
import com.chrisV.BasicFinancialApp.repository.CheckingAccountRepo;
import com.chrisV.BasicFinancialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    UserRepo repo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    CheckingAccountRepo checkingAccountRepo;

    public AccountResponseDTO addCheckingAccountToUser(Long userId, CheckingAccountRequestDTO accountDTO) {
        User user = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Account accountEntity = AccountMapper.fromRequestDTOToEntity(accountDTO);
        CheckingAccountDetails checkingDetails = new CheckingAccountDetails();
        checkingDetails.setAccount(accountEntity);
        checkingDetails.setOverdraftLimit(accountDTO.getOverdraftLimit());
        checkingDetails.setMonthlyFee(accountDTO.getMonthlyFee());
        checkingDetails.setMinimumBalance(accountDTO.getMinimumBalance());

        checkingAccountRepo.save(checkingDetails);

        // set bidirectional relationship
        accountEntity.setUser(user);
        user.addAccount(accountEntity);
        repo.save(user);
        return AccountMapper.fromEntityToResponseDTO(accountEntity);
    }

    public CheckingAccountResponseDTO getCheckingAccountInfo(Long accountId) {
        return accountRepo.findCheckingAccountDetailsById(accountId).orElseThrow(() -> new RuntimeException("Checking account not found with id: " + accountId));

    }




}
