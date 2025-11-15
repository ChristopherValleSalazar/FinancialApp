package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.*;
import com.chrisV.BasicFinancialApp.mapper.AccountMapper;
import com.chrisV.BasicFinancialApp.mapper.UserMapper;
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

    public UserResponseDTO createUser(UserRequestDTO user) {
        if(repo.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }

        User savingUser = UserMapper.toEntityFromRequestDTO(user);
        repo.save(savingUser);
        return UserMapper.fromEntityToResponseDTO(savingUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> userDTOS = repo.findAll().stream().
                map(UserMapper::fromEntityToResponseDTO).toList();

        if(userDTOS.isEmpty()) {
            return null;
        }

        return userDTOS;
    }

    public UserResponseDTO getUserById(Long id) {
        User user = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        if(user == null) {
            return null;
        }
        return UserMapper.fromEntityToResponseDTO(user);
    }

    public User updateOnlyNameUser(UserUpdateNameDTO userUpdate, Long id) {
//        create exception if resource is not found
        User existingUser = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        if(userUpdate.getFirstName() != null) existingUser.setFirstName(userUpdate.getFirstName());
        if(userUpdate.getLastName() != null) existingUser.setLastName(userUpdate.getLastName());

        //make custom QUERY for partial update if needed
        return repo.save(existingUser);
    }

    public AccountResponseDTO addAccountToUser(Long userId, AccountRequestDTO account) {
        User user = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Account accountEntity = AccountMapper.fromRequestDTOToEntity(account);

        // set bidirectional relationship
        accountEntity.setUser(user);
        user.addAccount(accountEntity);
        repo.save(user);
        return AccountMapper.fromEntityToResponseDTO(accountEntity);
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
