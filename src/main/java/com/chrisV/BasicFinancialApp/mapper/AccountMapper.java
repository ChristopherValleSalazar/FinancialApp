package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.account.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.account.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.dto.account.CheckingAccountRequest;
import com.chrisV.BasicFinancialApp.dto.account.CheckingAccountResponse;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.CheckingAccountDetails;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponseDTO fromEntityToResponseDTO(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setAccountType(account.getAccountType());
        dto.setBalance(account.getBalance());
        dto.setBankName(account.getBankName());
        dto.setNotes(account.getNotes());
        dto.setNickname(account.getNickname());

        AccountResponseDTO detailedDTO;

        switch(account.getAccountType()) {
            case CHECKING -> detailedDTO =  checkingAccountMapper(dto, account);
            // Add other account types here as needed
            default -> detailedDTO = null;
        }
        return detailedDTO;
    }

    public AccountResponseDTO fromEntityToResponseDTOWithoutDetails(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setAccountType(account.getAccountType());
        dto.setBalance(account.getBalance());
        dto.setBankName(account.getBankName());
        dto.setNotes(account.getNotes());
        dto.setNickname(account.getNickname());
        return dto;
    }

    private AccountResponseDTO checkingAccountMapper(AccountResponseDTO dto, Account account) {
        CheckingAccountDetails checkingDetails = account.getCheckingAccountDetails();
        CheckingAccountResponse checkingDTO = new CheckingAccountResponse();

        if (checkingDetails != null) {
            checkingDTO.setOverdraftLimit(checkingDetails.getOverdraftLimit());
            checkingDTO.setMonthlyFee(checkingDetails.getMonthlyFee());
            checkingDTO.setMinimumBalance(checkingDetails.getMinimumBalance());
            dto.setAccountDetails(checkingDTO);
        }
        return dto;
    }

    public Account fromRequestDTOToEntity(AccountRequestDTO dto) {
        Account account = new Account();
        // Assuming AccountRequestDTO has similar fields as Account
        account.setAccountType(dto.getAccountType());
        account.setBalance(dto.getBalance());
        account.setBankName(dto.getBankName());
        account.setNotes(dto.getNotes());
        account.setNickname(dto.getNickname());

        CheckingAccountRequest checkingDTO = (CheckingAccountRequest) dto.getAccountDetails();

        CheckingAccountDetails checkingDetails = new CheckingAccountDetails();
        checkingDetails.setOverdraftLimit(checkingDTO.getOverdraftLimit());
        checkingDetails.setMonthlyFee(checkingDTO.getMonthlyFee());
        checkingDetails.setMinimumBalance(checkingDTO.getMinimumBalance());

        account.setCheckingAccountDetails(checkingDetails);
        return account;
    }
}
