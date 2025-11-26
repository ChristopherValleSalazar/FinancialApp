package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.model.Account;

public class AccountMapper {

    public static AccountResponseDTO fromEntityToResponseDTO(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setAccountType(account.getAccountType());
        dto.setBalance(account.getBalance());
        dto.setCurrency(account.getCurrency());
//        dto.setInterestRate(account.getInterestRate());
        dto.setIsActive(account.getIsActive());
        dto.setBankName(account.getBankName());
        dto.setNotes(account.getNotes());
        dto.setNickname(account.getNickname());
        return dto;
    }

    public static Account fromRequestDTOToEntity(AccountRequestDTO dto) {
        Account account = new Account();
        // Assuming AccountRequestDTO has similar fields as Account
        account.setAccountType(dto.getAccountType());
        account.setBalance(dto.getBalance());
        account.setCurrency(dto.getCurrency());
//        account.setInterestRate(dto.getInterestRate());
        account.setIsActive(dto.getIsActive());
        account.setBankName(dto.getBankName());
        account.setNotes(dto.getNotes());
        account.setNickname(dto.getNickname());
        return account;
    }



}
