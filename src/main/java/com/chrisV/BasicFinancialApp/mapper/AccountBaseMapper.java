package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.account.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.account.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountBaseMapper {

    // Ignoring accountDetails (unique fields per account type), only mapping base fields for ALL account types
    @Mapping(target = "accountDetails", ignore = true)
    AccountResponseDTO accountToAccountResponseDTOWithoutDetails(Account account);

    @Mapping(target = "checkingAccountDetails", ignore = true)
    Account fromRequestDtoToEntity(AccountRequestDTO dto);

}
