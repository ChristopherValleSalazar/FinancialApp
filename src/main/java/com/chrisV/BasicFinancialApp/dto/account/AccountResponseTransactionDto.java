package com.chrisV.BasicFinancialApp.dto.account;

import com.chrisV.BasicFinancialApp.dto.transaction.TransactionResponse;
import com.chrisV.BasicFinancialApp.model.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Dto to return only one transaction, once user creates a transaction for their account
public class AccountResponseTransactionDto {
    private BigDecimal balance;
    private String bankName;
    private String nickname;

    private TransactionResponse latestTransaction;
}
