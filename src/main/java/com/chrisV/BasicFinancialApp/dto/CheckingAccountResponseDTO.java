package com.chrisV.BasicFinancialApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CheckingAccountResponseDTO {
    private BigDecimal overdraftLimit;
    private BigDecimal monthlyFee;
    private BigDecimal minimumBalance;
}
