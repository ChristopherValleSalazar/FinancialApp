package com.chrisV.BasicFinancialApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequestDTO {
    private String accountType;
    private String currency;
    private BigDecimal interestRate;
    private Long accountNumber;
    private Long routingNumber;
    private Boolean isActive;
    private String bankName;
    private String notes;
    private String nickname;
    private BigDecimal balance;
    private UserRequestDTO user;

    public AccountRequestDTO() { }

    public AccountRequestDTO(String accountType, String currency, BigDecimal interestRate, Boolean isActive, String bankName, String notes, String nickname, BigDecimal balance) {
        this.balance = balance;
        this.accountType = accountType;
        this.currency = currency;
        this.interestRate = interestRate;
        this.isActive = isActive;
        this.bankName = bankName;
        this.notes = notes;
        this.nickname = nickname;
    }

}
