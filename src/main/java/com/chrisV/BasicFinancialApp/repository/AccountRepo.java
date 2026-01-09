package com.chrisV.BasicFinancialApp.repository;

import com.chrisV.BasicFinancialApp.model.account.Account;
import com.chrisV.BasicFinancialApp.model.account.AccountType;
import com.chrisV.BasicFinancialApp.model.account.Transaction;
import com.chrisV.BasicFinancialApp.model.transaction.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    @Query("SELECT a from Account a " +
           "LEFT JOIN FETCH a.checkingAccountDetails cd " +
            "Where a.id = :id")
    Optional<Account> findByIdWithCheckingDetails(@Param("id") Long id);

    List<Account> findAllByUserId(Long userId);
    Account deleteByIdAndUserId(Long accountId, Long userId);

    @Query("SELECT SUM(a.balance) FROM Account a " +
            "WHERE a.user.id = :userId AND a.accountType = :accountType")
    BigDecimal findTotalBalanceByUserIdAndAccountType(@Param("userId") Long userId, @Param("accountType")AccountType accountType);



    @Query("SELECT t FROM Account a " +
           "JOIN a.transactions t " +
           "WHERE a.user.id = :userId " +
           "AND a.id = :accountId " +
           "AND t.category = :transactionCategory"
    )
    List<Transaction> findTransactionsPerCategoryByUserIdAndAccountId(@Param("userId") Long userId, @Param("accountId") Long accountId, @Param("transactionCategory") TransactionCategory transactionCategory);


    //TODO: Create custome query to get simple account display information instead of querying for the whole account info

}
