package com.chrisV.BasicFinancialApp.controller;

import com.chrisV.BasicFinancialApp.dto.user.UserResponseDTO;
import com.chrisV.BasicFinancialApp.dto.user.UserUpdateEmailDTO;
import com.chrisV.BasicFinancialApp.dto.user.UserUpdateNameDTO;
import com.chrisV.BasicFinancialApp.dto.user.UserUpdateUsernameDTO;
import com.chrisV.BasicFinancialApp.mapper.UserMapper;
import com.chrisV.BasicFinancialApp.model.user.User;
import com.chrisV.BasicFinancialApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
//@CrossOrigin("http://localhost:5173")
public class UserController {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        //logic
        List<UserResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //TODO: Transfer to service, from here to last method
    @PatchMapping("/update-name/{id}")
    public ResponseEntity<UserUpdateNameDTO> updateUserNames(@PathVariable Long id, @RequestBody UserUpdateNameDTO userUpdate) {

        //check each field and update except id and accounts
        User updatedUser = userService.updateOnlyNameUser(userUpdate, id);
        return new ResponseEntity<>(mapper.userToUserUpdateNameDTO(updatedUser), HttpStatus.OK); //TODO: Move to service
    }

    @PatchMapping("/update-username/{id}")
    public ResponseEntity<UserUpdateUsernameDTO> updateUsername(
            @PathVariable Long id,
            @RequestBody UserUpdateUsernameDTO userUpdate) {

        User updatedUser = userService.updateOnlyUsernameUser(userUpdate, id);
        return new ResponseEntity<>(mapper.userToUserUpdateUsernameDTO(updatedUser), HttpStatus.OK); //TODO: Move to service
    }

    @PatchMapping("/update-email/{id}")
    public ResponseEntity<UserUpdateEmailDTO> updateEmail(
            @PathVariable Long id,
            @RequestBody UserUpdateEmailDTO userUpdate) {

        User updatedUser = userService.updateOnlyEmailUser(userUpdate, id);
        return new ResponseEntity<>(mapper.userToUserUpdateEmailDTO(updatedUser), HttpStatus.OK); //TODO: Move to service
    }
}
