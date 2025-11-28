package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.dto.CheckingAccountRequest;
import com.chrisV.BasicFinancialApp.dto.CheckingAccountResponse;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.CheckingAccountDetails;

public class AccountMapper {

    public static AccountResponseDTO fromEntityToResponseDTO(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setAccountType(account.getAccountType());
        dto.setBalance(account.getBalance());
        dto.setBankName(account.getBankName());
        dto.setNotes(account.getNotes());
        dto.setNickname(account.getNickname());

        CheckingAccountResponse checkingDTO = new CheckingAccountResponse();
        CheckingAccountDetails checkingDetails = account.getCheckingAccountDetails();
        if (checkingDetails != null) {
            checkingDTO.setOverdraftLimit(checkingDetails.getOverdraftLimit());
            checkingDTO.setMonthlyFee(checkingDetails.getMonthlyFee());
            checkingDTO.setMinimumBalance(checkingDetails.getMinimumBalance());
            dto.setAccountDetails(checkingDTO);
        }

        return dto;
    }

    public static Account fromRequestDTOToEntity(AccountRequestDTO dto) {
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
