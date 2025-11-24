package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.mapper.AccountMapper;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    UserRepo repo;

    public AccountResponseDTO addAccountToUser(Long userId, AccountRequestDTO account) {
        User user = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Account accountEntity = AccountMapper.fromRequestDTOToEntity(account);

        // set bidirectional relationship
        accountEntity.setUser(user);
        user.addAccount(accountEntity);
        repo.save(user);
        return AccountMapper.fromEntityToResponseDTO(accountEntity);
    }

}
