package com.chrisV.BasicFinancialApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountResponseDTO {
    private String accountType;
    private BigDecimal balance;
    private String currency;
    private BigDecimal interestRate;
    private Boolean isActive;
    private String bankName;
    private String notes;
    private String nickname;

    public AccountResponseDTO() {
    }

    public AccountResponseDTO(String accountType, BigDecimal balance, String currency, BigDecimal interestRate, Boolean isActive, String bankName, String notes, String nickname, LocalDateTime updatedAt) {
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        this.interestRate = interestRate;
        this.isActive = isActive;
        this.bankName = bankName;
        this.notes = notes;
        this.nickname = nickname;
    }
}
