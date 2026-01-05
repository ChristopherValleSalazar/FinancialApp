package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.account.*;
import com.chrisV.BasicFinancialApp.dto.transaction.TransactionRequest;
import com.chrisV.BasicFinancialApp.dto.transaction.TransactionResponse;
import com.chrisV.BasicFinancialApp.mapper.AccountBaseMapper;
import com.chrisV.BasicFinancialApp.mapper.AccountMapper;
import com.chrisV.BasicFinancialApp.mapper.TransactionMapper;
import com.chrisV.BasicFinancialApp.model.account.Account;
import com.chrisV.BasicFinancialApp.model.account.AccountType;
import com.chrisV.BasicFinancialApp.model.account.Transaction;
import com.chrisV.BasicFinancialApp.model.transaction.TransactionType;
import com.chrisV.BasicFinancialApp.model.user.User;
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
    AccountBaseMapper accountBaseMapper;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    TransactionMapper transactionMapper;

    //TODO: make more generic for different account types
//    @Transactional(readOnly = true)
    public AccountResponseDTO addCheckingAccountToUser(Long userId, AccountRequestDTO accountDTO) {
        User user = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Account accountEntity = accountMapper.fromRequestDtoToEntity(accountDTO);

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

    @Transactional
    public AccountResponseDTO updateAccount(Long accountId, AccountUpdateRequestDTO accountRequestDTO) {
        Account existingAccount = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

        accountBaseMapper.updateAccountFromDto(accountRequestDTO, existingAccount);

        accountRepo.save(existingAccount);
        return accountMapper.fromEntityToResponseDTO(existingAccount);
    }

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest transactionDto) {
        Optional<Account> account = accountRepo.findById(transactionDto.getFromAccountId());

        Transaction transaction =  transactionMapper.dtoToEntity(transactionDto);
        account.get().addTransaction(transaction);

        if(transaction.getType() == TransactionType.EXPENSE) {
            account.get().applyExpense(transaction.getAmount());
        }
        else if(transaction.getType() == TransactionType.INCOME) {
            account.get().applyIncome(transaction.getAmount());
        }

        accountRepo.save(account.get());
        return transactionMapper.transactionToTransactionresponse(transaction);
    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> getAllTransactionsByAccountId(Long accountId) {
        Optional<Account> account = accountRepo.findById(accountId);

        List<TransactionResponse> transactionResponses = account.get().getTransactions().stream().map(transaction -> {
            TransactionResponse dto = transactionMapper.transactionToTransactionresponse(transaction);
            return dto;
        }).toList();
        return transactionResponses;
    }
}
