package com.chrisV.BasicFinancialApp.dto;

import com.chrisV.BasicFinancialApp.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckingAccountResponseDTO {
    private AccountType accountType;
    private String bankName;
    private String notes;
    private String nickname;
    private BigDecimal balance;
    private BigDecimal overdraftLimit;
    private BigDecimal monthlyFee;
    private BigDecimal minimumBalance;
}
