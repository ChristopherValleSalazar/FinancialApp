package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public User createUser(User user) {
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public User updateFullUser(User user) {




        return repo.save(user);
    }

    public User addAccountToUser(Long userId, Account account) {
        User user = getUserById(userId);
        if(user == null) {
            return null;
        }

        // set bidirectional relationship
        account.setUser(user);
        user.getAccounts().add(account);
        return repo.save(user);
    }









}
