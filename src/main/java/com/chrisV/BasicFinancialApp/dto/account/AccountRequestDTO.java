package com.chrisV.BasicFinancialApp.dto.account;

import com.chrisV.BasicFinancialApp.dto.user.UserRequestDTO;
import com.chrisV.BasicFinancialApp.model.account.AccountType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
//DTO for CHECKING ACCOUNT
public class AccountRequestDTO {
    private AccountType accountType;
    private String bankName;
    private String notes;
    private String nickname;
    private BigDecimal balance;
    private UserRequestDTO user;

    // Polymorphic deserialization based on accountType
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "accountType",
            visible = false)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = CheckingAccountRequest.class, name = "CHECKING")
    })
    private AccountDetails accountDetails;
}
