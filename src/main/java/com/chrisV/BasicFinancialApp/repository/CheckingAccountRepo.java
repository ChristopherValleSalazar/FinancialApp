package com.chrisV.BasicFinancialApp.repository;

import com.chrisV.BasicFinancialApp.model.account.CheckingAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingAccountRepo extends JpaRepository<CheckingAccountDetails, Long> {
}
