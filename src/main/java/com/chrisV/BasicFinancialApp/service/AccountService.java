package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.account.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.account.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.dto.account.CheckingAccountResponse;
import com.chrisV.BasicFinancialApp.mapper.AccountMapper;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.AccountType;
import com.chrisV.BasicFinancialApp.model.CheckingAccountDetails;
import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.repository.AccountRepo;
import com.chrisV.BasicFinancialApp.repository.CheckingAccountRepo;
import com.chrisV.BasicFinancialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    UserRepo repo;

    @Autowired
    AccountRepo accountRepo;

    public AccountResponseDTO addCheckingAccountToUser(Long userId, AccountRequestDTO accountDTO) {
        User user = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Account accountEntity = AccountMapper.fromRequestDTOToEntity(accountDTO);

        // set bidirectional relationship
        accountEntity.setUser(user);
        user.addAccount(accountEntity);
        repo.save(user);
        return AccountMapper.fromEntityToResponseDTO(accountEntity);
    }

    @Transactional(readOnly = true)
    public Optional<AccountResponseDTO> findAccountById(Long id) {
        return accountRepo.findByIdWithCheckingDetails(id)
                .map(this::toDto);
    }

    private AccountResponseDTO toDto(Account a) {
        AccountResponseDTO dto = AccountMapper.fromEntityToResponseDTO(a);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<AccountResponseDTO> getAllCheckingAccountsByUserId(Long userId) {
        List<AccountResponseDTO> accounts = accountRepo.findAllByUserId(userId)
                .stream()
                .filter(dto -> dto.getAccountType() == AccountType.CHECKING)
                .map(account -> AccountMapper.fromEntityToResponseDTO(account))
                .toList();
        return accounts;

    }

    @Transactional
    public AccountResponseDTO deleteAccount(Long userId, Long accountId) {
        User user = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Account account = accountRepo.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found with id: " + accountId));

        if (account != null && account.getUser().getId().equals(userId)) {
            return AccountMapper.fromEntityToResponseDTO(accountRepo.deleteByIdAndUserId(accountId, userId));
        }
        return null;
    }
}
