package com.chrisV.BasicFinancialApp.dto.transaction;

import com.chrisV.BasicFinancialApp.model.transaction.TransactionCategory;
import com.chrisV.BasicFinancialApp.model.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
//TODO: figure out a way to return timestamp once created transaction
public class TransactionResponse {
    private TransactionType type;
    private String description;
    private TransactionCategory category;
    private BigDecimal amount;
}
