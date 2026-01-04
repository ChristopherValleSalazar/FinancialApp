package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.account.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.account.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.dto.account.AccountUpdateRequestDTO;
import com.chrisV.BasicFinancialApp.dto.transaction.TransactionRequest;
import com.chrisV.BasicFinancialApp.dto.transaction.TransactionResponse;
import com.chrisV.BasicFinancialApp.mapper.AccountMapper;
import com.chrisV.BasicFinancialApp.mapper.TransactionMapper;
import com.chrisV.BasicFinancialApp.model.account.Account;
import com.chrisV.BasicFinancialApp.model.account.AccountType;
import com.chrisV.BasicFinancialApp.model.transaction.Transaction;
import com.chrisV.BasicFinancialApp.model.transaction.TransactionType;
import com.chrisV.BasicFinancialApp.model.user.User;
import com.chrisV.BasicFinancialApp.repository.AccountRepo;
import com.chrisV.BasicFinancialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    @Autowired
    TransactionMapper transactionMapper;

    //TODO: make more generic for different account types
//    @Transactional(readOnly = true)
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

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest transactionDto) {
        Optional<Account> account = accountRepo.findById(transactionDto.getFromAccountId());

        Transaction transaction =  transactionMapper.dtoToEntity(transactionDto);
        transaction.setAccount(account.get());

        //Make better after
        account.get().getTransactions().add(transaction);
        account.get().setBalance(calculateNewBalance(account.get().getBalance(), transaction.getAmount(), transaction.getType()));
        accountRepo.save(account.get());

        return transactionMapper.transactionToTransactionresponse(transaction);
    }

    //temporary return String
    //TODO: improve handling of insufficient funds
    //Method somehow should notify user of insufficient funds and allow them to accept fees or cancel transaction
    //even better allow the user to set preferences for insufficient funds that should match their bank account settings
    //that way its only a notification about the fee, and telling them their transaction will go through with the fee

//    private String feeConfirmMessage(BigDecimal currBalance, BigDecimal amount) {
//
//    }

    //notify if insufficient funds to user
    private boolean isSufficientFunds(BigDecimal currBalance, BigDecimal amount) {
        return currBalance.compareTo(amount) >= 0; //if true then currBalance is greater than currTransaction amount
    }

    private BigDecimal calculateFee(BigDecimal currBalance, BigDecimal amount) {
        BigDecimal fee = BigDecimal.ZERO;

        if(!isSufficientFunds(currBalance, amount)) { // check if currBalance is less than transaction amount
            fee = amount.multiply(new BigDecimal("0.02")); // 2% fee
        }

        return fee;
    }

    private BigDecimal calculateNewBalance(BigDecimal currBalance, BigDecimal amount, TransactionType type) {
        BigDecimal fee = calculateFee(currBalance, amount);

        System.out.println(amount); //temporary print statement


        if(type == TransactionType.EXPENSE) { //only invoke calculate fee if check is false
            if (fee.compareTo(BigDecimal.ZERO) > 0) {
                amount = amount.add(fee);
                String message = "Insufficient funds for this transaction. A fee of " + fee.abs() + " will be applied."; // call this to notify user
                System.out.println(message); //temporary print statement
            }
            currBalance = currBalance.subtract(amount);
            System.out.println(amount); //temporary print statement
        }

        else if(type == TransactionType.INCOME) {
            currBalance = currBalance.add(amount);
        }
        System.out.println(amount); //temporary print statement
        return currBalance;
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
