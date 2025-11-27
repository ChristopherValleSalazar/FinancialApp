package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.CheckingAccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.model.Account;

public class AccountMapper {

    public static AccountResponseDTO fromEntityToResponseDTO(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setAccountType(account.getAccountType());
        dto.setBalance(account.getBalance());
//        dto.setCurrency(account.getCurrency());
//        dto.setInterestRate(account.getInterestRate());
        dto.setBankName(account.getBankName());
        dto.setNotes(account.getNotes());
        dto.setNickname(account.getNickname());
        return dto;
    }

    public static Account fromRequestDTOToEntity(CheckingAccountRequestDTO dto) {
        Account account = new Account();
        // Assuming CheckingAccountRequestDTO has similar fields as Account
        account.setAccountType(dto.getAccountType());
        account.setBalance(dto.getBalance());
//        account.setCurrency(dto.getCurrency());
//        account.setInterestRate(dto.getInterestRate());
        account.setBankName(dto.getBankName());
        account.setNotes(dto.getNotes());
        account.setNickname(dto.getNickname());
        return account;
    }



}
