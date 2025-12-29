package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.account.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountUpgradedMapper {

    AccountUpgradedMapper INSTANCE = Mappers.getMapper(AccountUpgradedMapper.class);

    AccountResponseDTO toAccountResponseDTO(Account account);



}
