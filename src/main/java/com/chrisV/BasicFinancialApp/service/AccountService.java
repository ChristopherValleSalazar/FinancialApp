package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.dto.CheckingAccountRequest;
import com.chrisV.BasicFinancialApp.dto.CheckingAccountResponse;
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

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    UserRepo repo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    CheckingAccountRepo checkingAccountRepo;

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
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setAccountType(a.getAccountType());
        dto.setBankName(a.getBankName());
        dto.setNotes(a.getNotes());
        dto.setNickname(a.getNickname());
        dto.setBalance(a.getBalance());

        // Only populate checkingDetails if this account is of type CHECKING (defensive)
        if (a.getAccountType() == AccountType.CHECKING) {
            CheckingAccountDetails ch = a.getCheckingAccountDetails();
            if (ch != null) {
                CheckingAccountResponse cdto = new CheckingAccountResponse();
                cdto.setOverdraftLimit(ch.getOverdraftLimit());
                cdto.setMonthlyFee(ch.getMonthlyFee());
                cdto.setMinimumBalance(ch.getMinimumBalance());
                dto.setAccountDetails(cdto);
            }
        }
        return dto;
    }







//    public CheckingAccountResponse getCheckingAccountInfo(Long accountId) {
//        return accountRepo.findCheckingAccountDetailsById(accountId).orElseThrow(() -> new RuntimeException("Checking account not found with id: " + accountId));

//    }




}
