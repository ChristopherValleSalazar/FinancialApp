package com.chrisV.BasicFinancialApp.repository;

import com.chrisV.BasicFinancialApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

}
