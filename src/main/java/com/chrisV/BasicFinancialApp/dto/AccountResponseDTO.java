package com.chrisV.BasicFinancialApp.dto;

import com.chrisV.BasicFinancialApp.model.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponseDTO {
    private AccountType accountType;
    private BigDecimal balance;
    private String bankName;
    private String notes;
    private String nickname;

    public AccountResponseDTO() {
    }

    public AccountResponseDTO(AccountType accountType, BigDecimal balance, String bankName, String notes, String nickname) {
        this.accountType = accountType;
        this.balance = balance;
        this.bankName = bankName;
        this.notes = notes;
        this.nickname = nickname;
    }
}
