package com.chrisV.BasicFinancialApp.service;

import com.chrisV.BasicFinancialApp.model.User;
import com.chrisV.BasicFinancialApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo repo;

    public List<User> getAllUsers() {
        return repo.findAll();
    }



}
