package com.chrisV.BasicFinancialApp.repository;

import com.chrisV.BasicFinancialApp.model.account.SavingsAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccountRepo extends JpaRepository<SavingsAccountDetails, Long> {
}
