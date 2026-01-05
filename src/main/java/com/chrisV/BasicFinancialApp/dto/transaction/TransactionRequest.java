package com.chrisV.BasicFinancialApp.dto.transaction;

import com.chrisV.BasicFinancialApp.model.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private TransactionType type;
    private Long fromAccountId; //maybe can use this instead of exposing id on URL
    private String description;
    private String category;
    private BigDecimal amount;
}

