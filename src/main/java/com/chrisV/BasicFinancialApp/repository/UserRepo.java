package com.chrisV.BasicFinancialApp.repository;

import com.chrisV.BasicFinancialApp.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    User findByUsername(String username);

}
