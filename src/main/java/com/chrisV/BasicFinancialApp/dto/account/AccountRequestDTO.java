package com.chrisV.BasicFinancialApp.dto.account;

import com.chrisV.BasicFinancialApp.dto.user.UserRequestDTO;
import com.chrisV.BasicFinancialApp.model.account.AccountType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
//DTO for CHECKING ACCOUNT
public class AccountRequestDTO {
    private AccountType accountType;
    private String bankName;
    private String notes;
    private String nickname;
    private UserRequestDTO user;
}
