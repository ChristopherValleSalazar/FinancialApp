package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.account.CheckingAccountRequest;
import com.chrisV.BasicFinancialApp.dto.account.CheckingAccountResponse;
import com.chrisV.BasicFinancialApp.model.account.CheckingAccountDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckingAccountDetailsMapper {

    // mapping only accountDetails to aid in type safe mapping in AccountMapper
    CheckingAccountResponse fromEntityToDto(CheckingAccountDetails entity);
    CheckingAccountDetails fromDtoToEntity(CheckingAccountRequest dto); //TODO: Change the object for the actual one

}
