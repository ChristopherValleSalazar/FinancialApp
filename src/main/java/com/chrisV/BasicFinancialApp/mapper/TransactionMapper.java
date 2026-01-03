package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.account.AccountResponseTransactionDto;
import com.chrisV.BasicFinancialApp.dto.transaction.TransactionRequest;
import com.chrisV.BasicFinancialApp.dto.transaction.TransactionResponse;
import com.chrisV.BasicFinancialApp.model.account.Account;
import com.chrisV.BasicFinancialApp.model.transaction.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction dtoToEntity(Account account, TransactionRequest transactionDto);

    AccountResponseTransactionDto entityToDto(Account account);
    TransactionResponse transactionToTransactionresponse(Transaction transaction);





}
