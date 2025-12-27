package com.chrisV.BasicFinancialApp.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CheckingAccountRequest implements AccountDetails {
    private BigDecimal overdraftLimit;
    private BigDecimal monthlyFee;
    private BigDecimal minimumBalance;
}
