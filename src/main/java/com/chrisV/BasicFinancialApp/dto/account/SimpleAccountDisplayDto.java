package com.chrisV.BasicFinancialApp.dto.account;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SimpleAccountDisplayDto {

    private String bankName;
    private BigDecimal balance;
    //TODO: Add current transactions this month, so income and expenses separately


}
