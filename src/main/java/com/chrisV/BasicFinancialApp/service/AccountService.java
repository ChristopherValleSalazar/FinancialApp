package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.account.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.account.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.dto.account.AccountUpdateRequestDTO;
import com.chrisV.BasicFinancialApp.mapper.AccountMapper;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.AccountType;
import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.repository.AccountRepo;
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

    @Autowired
    AccountMapper accountMapper;

    public AccountResponseDTO addCheckingAccountToUser(Long userId, AccountRequestDTO accountDTO) {
        User user = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Account accountEntity = accountMapper.fromRequestDtoToEntity(accountDTO);

        // set bidirectional relationship
        accountEntity.setUser(user);
        user.addAccount(accountEntity);
        repo.save(user);
        return accountMapper.fromEntityToResponseDTO(accountEntity);
    }

    @Transactional(readOnly = true)
    public Optional<AccountResponseDTO> findAccountById(Long id) {
        return accountRepo.findByIdWithCheckingDetails(id)
                .map(this::toDto); // map Account entity to AccountResponseDTO
    }

    private AccountResponseDTO toDto(Account entity) {
        return accountMapper.fromEntityToResponseDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<AccountResponseDTO> getAllCheckingAccountsByUserId(Long userId) {
        return accountRepo.findAllByUserId(userId)
                .stream()
                .filter(dto -> dto.getAccountType() == AccountType.CHECKING)
                .map(account -> accountMapper.fromEntityToResponseDTO(account))
                .toList();

    }

    @Transactional
    public AccountResponseDTO deleteAccount(Long userId, Long accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found with id: " + accountId));

        if (account != null && account.getUser().getId().equals(userId)) {
            return accountMapper.fromEntityToResponseDTO(accountRepo.deleteByIdAndUserId(accountId, userId)); //TODO: return a simpler dto since its a deletion
        }
        return null;
    }

    @Transactional(readOnly = true)
    public AccountResponseDTO updateAccount(Long accountId, AccountUpdateRequestDTO accountRequestDTO) {
        Account existingAccount = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        //TODO: figure out how to handle account type changes properly
        //TODO: figure out how to remove these null checks with mapstruct

        // Update fields
        if(accountRequestDTO.getAccountType() != null) {
            existingAccount.setAccountType(accountRequestDTO.getAccountType());
        }
        if(accountRequestDTO.getBankName() != null) {
            existingAccount.setBankName(accountRequestDTO.getBankName());
        }
        if(accountRequestDTO.getNickname() != null) {
            existingAccount.setNickname(accountRequestDTO.getNickname());
        }
        if(accountRequestDTO.getNotes() != null) {
            existingAccount.setNotes(accountRequestDTO.getNotes());
        }

        accountRepo.save(existingAccount);
        return accountMapper.fromEntityToResponseDTO(existingAccount);
    }
}
