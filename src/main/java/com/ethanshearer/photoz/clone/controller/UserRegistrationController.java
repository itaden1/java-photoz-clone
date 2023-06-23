package com.ethanshearer.photoz.clone.controller;

import com.ethanshearer.photoz.clone.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerUser(String email, String password) {
        userService.registerUser(email, password);
    }
}
