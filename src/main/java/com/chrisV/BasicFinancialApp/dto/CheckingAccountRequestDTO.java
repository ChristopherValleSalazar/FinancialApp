package com.chrisV.BasicFinancialApp.dto;

import com.chrisV.BasicFinancialApp.model.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
//DTO for CHECKING ACCOUNT
public class CheckingAccountRequestDTO {

    private AccountType accountType;
//    private String currency;
//    private BigDecimal interestRate;
    private String bankName;
    private String notes;
    private String nickname;
    private BigDecimal balance;
    private UserRequestDTO user;
    private BigDecimal overdraftLimit;
    private BigDecimal monthlyFee;
    private BigDecimal minimumBalance;

    public CheckingAccountRequestDTO() { }

    public CheckingAccountRequestDTO(AccountType accountType, String bankName, String notes, String nickname, BigDecimal balance,
                                     UserRequestDTO user, BigDecimal overdraftLimit, BigDecimal monthlyFee, BigDecimal minimumBalance) {
        this.accountType = accountType;
        this.bankName = bankName;
        this.notes = notes;
        this.nickname = nickname;
        this.balance = balance;
        this.user = user;
        this.overdraftLimit = overdraftLimit;
        this.monthlyFee = monthlyFee;
        this.minimumBalance = minimumBalance;
    }

}
