package com.chrisV.BasicFinancialApp.repository;

import com.chrisV.BasicFinancialApp.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
