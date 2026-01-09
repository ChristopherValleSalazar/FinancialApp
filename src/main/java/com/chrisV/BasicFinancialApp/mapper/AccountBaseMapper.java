package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.account.*;
import com.chrisV.BasicFinancialApp.model.account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE

)
public interface AccountBaseMapper {

    //TODO:Make this class for DTO's that only have the base fields or to modify this into different methods for multiple endpoints
    // for a simple mapping with the ignore = true property to skip unique fields mapping

    AccountResponseDTO entityToAccountResponseDto(Account account);
    Account fromRequestDtoToEntity(AccountRequestDTO dto);

    //ignore fields if null in dto
    void updateAccountFromDto(AccountUpdateRequestDTO dto, @MappingTarget Account account);

    SimpleAccountDisplayDto accountEntityToSimpleDisplayAccount(Account account);



}
