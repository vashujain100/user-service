package com.example.tekdev.userservice.controllers;

import com.example.tekdev.userservice.models.User;
import com.example.tekdev.userservice.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AllUsersController {
    @Autowired
    private UserService userService;

    @RequestMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/addUser")
    public void addUser(@RequestBody UserDTO userDTO) {
        User newUser = new User(userDTO.userEmail,userDTO.userName);
        userService.addUser(newUser);
    }
    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestBody UserEmailUnwrapper userEmailUnwrapper) {
        userService.deleteUser(userEmailUnwrapper.userEmail);
    }

    @Data
    static class UserDTO {
        private String userName;
        private String userEmail;
    }
    @Data
    static class UserEmailUnwrapper {
        private String userEmail;
    }
}
