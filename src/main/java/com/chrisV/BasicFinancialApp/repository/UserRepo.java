package com.chrisV.BasicFinancialApp.repository;

import com.chrisV.BasicFinancialApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
