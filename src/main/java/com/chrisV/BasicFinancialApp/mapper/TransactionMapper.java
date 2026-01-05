package com.chrisV.BasicFinancialApp.mapper;

import com.chrisV.BasicFinancialApp.dto.transaction.TransactionRequest;
import com.chrisV.BasicFinancialApp.dto.transaction.TransactionResponse;
import com.chrisV.BasicFinancialApp.model.account.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "account", ignore = true)
    @Mapping(target = "id", ignore = true)
    Transaction dtoToEntity(TransactionRequest transactionDto);

    TransactionResponse transactionToTransactionresponse(Transaction transaction);





}
