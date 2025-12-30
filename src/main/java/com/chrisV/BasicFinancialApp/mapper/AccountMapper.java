package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.account.AccountRequestDTO;
import com.chrisV.BasicFinancialApp.dto.account.AccountResponseDTO;
import com.chrisV.BasicFinancialApp.dto.account.CheckingAccountRequest;
import com.chrisV.BasicFinancialApp.model.Account;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        uses = {
            AccountBaseMapper.class,
            CheckingAccountDetailsMapper.class
        })
public abstract class AccountMapper {

    @Autowired
    protected AccountBaseMapper baseMapper;

    @Autowired
    protected CheckingAccountDetailsMapper checkingMapper;

    // mapping method for full mapping with generic & unique fields from the entity to the DTO
    public AccountResponseDTO fromEntityToResponseDTO(Account account) {

        AccountResponseDTO dto = baseMapper.accountToAccountResponseDTOWithoutDetails(account);

        switch(account.getAccountType()) {
            case CHECKING -> dto.setAccountDetails(checkingMapper.fromEntityToDto(account.getCheckingAccountDetails()));
            default -> throw new IllegalArgumentException("Unsupported account type: " + account.getAccountType()); //TODO: custom exception of handle this elsewhere
        }
        return dto;
    }

    // mapping method for full mapping with generic & unique fields from the DTO to the entity
    public Account fromRequestDtoToEntity(AccountRequestDTO dto) {
        Account account = baseMapper.fromRequestDtoToEntity(dto);

        switch(account.getAccountType()) {
            case CHECKING -> account.setCheckingAccountDetails(checkingMapper.fromDtoToEntity((CheckingAccountRequest) dto.getAccountDetails()));
            default -> throw new IllegalArgumentException("Unsupported account type: " + account.getAccountType()); //TODO: custom exception of handle this elsewhere
        }
        return account;
    }
}
