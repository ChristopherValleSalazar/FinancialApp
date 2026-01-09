package com.chrisV.BasicFinancialApp.dto.transaction;

import com.chrisV.BasicFinancialApp.model.transaction.TransactionCategory;
import com.chrisV.BasicFinancialApp.model.transaction.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionRequest {
    private TransactionType type;
    private Long fromAccountId; //maybe can use this instead of exposing id on URL
    private String description;
    private TransactionCategory category;
    private BigDecimal amount;
}

