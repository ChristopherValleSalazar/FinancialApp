package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.UserUpdateEmailDTO;
import com.chrisV.BasicFinancialApp.dto.UserUpdateUsernameDTO;
import com.chrisV.BasicFinancialApp.dto.UserUpdateNameDTO;
import com.chrisV.BasicFinancialApp.model.Account;
import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User updateOnlyNameUser(UserUpdateNameDTO userUpdate, Long id) {
//        create exception if resource is not found
        User existingUser = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        if(userUpdate.getFirstName() != null) existingUser.setFirstName(userUpdate.getFirstName());
        if(userUpdate.getLastName() != null) existingUser.setLastName(userUpdate.getLastName());

        //make custom QUERY for partial update if needed
        return repo.save(existingUser);
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


    public User updateOnlyUsernameUser(UserUpdateUsernameDTO userUpdate, Long id) {
        Optional<User> existingUser = repo.findById(id);
        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }

        if(repo.existsByUsername(userUpdate.getUsername())) {
            throw new RuntimeException("Username already exists: " + userUpdate.getUsername());
        }

        User user = existingUser.get();
        user.setUsername(userUpdate.getUsername());
        return repo.save(user);
    }

    public User updateOnlyEmailUser(UserUpdateEmailDTO userUpdate, Long id) {
        Optional<User> existingUser = repo.findById(id);
        if (existingUser.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }

        User user = existingUser.get();
        user.setEmail(userUpdate.getEmail());
        return repo.save(user);
    }
}
