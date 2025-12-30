package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.dto.user.UserResponseDTO;
import com.chrisV.BasicFinancialApp.dto.user.UserUpdateEmailDTO;
import com.chrisV.BasicFinancialApp.dto.user.UserUpdateNameDTO;
import com.chrisV.BasicFinancialApp.dto.user.UserUpdateUsernameDTO;
import com.chrisV.BasicFinancialApp.mapper.UserMapper;
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

    @Autowired
    private UserMapper mapper;

    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> userDTOS = repo.findAll().stream().
                map(mapper::userToUserResponseDTO).toList();

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
        return mapper.userToUserResponseDTO(user);
    }

    public User updateOnlyNameUser(UserUpdateNameDTO userUpdate, Long id) {
//        create exception if resource is not found
        User existingUser = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        if(userUpdate.getFirstName() != null) existingUser.setFirstName(userUpdate.getFirstName());
        if(userUpdate.getLastName() != null) existingUser.setLastName(userUpdate.getLastName());

        //make custom QUERY for partial update if needed
        return repo.save(existingUser);
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
