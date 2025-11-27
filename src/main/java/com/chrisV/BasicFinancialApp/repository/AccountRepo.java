package com.chrisV.BasicFinancialApp.repository;

import com.chrisV.BasicFinancialApp.dto.CheckingAccountResponseDTO;
import com.chrisV.BasicFinancialApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    @Query("SELECT new com.chrisV.BasicFinancialApp.dto.CheckingAccountResponseDTO(" +
           "a.accountType, a.bankName, a.notes, a.nickname, a.balance, " +
           "cad.overdraftLimit, cad.monthlyFee, cad.minimumBalance) " +
           "FROM Account a JOIN CheckingAccountDetails cad ON a.id = cad.account.id " +
           "WHERE a.id = :accountId")
    Optional<CheckingAccountResponseDTO> findCheckingAccountDetailsById(Long accountId);


}
