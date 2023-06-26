package com.ethanshearer.photoz.clone.controller;

import com.ethanshearer.photoz.clone.dto.UserCredentialsDTO;
import com.ethanshearer.photoz.clone.service.UserService;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserCredentialsDTO userCredentials) {
        userService.registerUser(userCredentials.getEmail(), userCredentials.getPassword());
    }
}
