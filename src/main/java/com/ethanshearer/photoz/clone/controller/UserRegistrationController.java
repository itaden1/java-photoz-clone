package com.ethanshearer.photoz.clone.controller;

import com.ethanshearer.photoz.clone.dto.UserCredentialsDTO;
import com.ethanshearer.photoz.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserCredentialsDTO userCredentials) {
        userService.registerUser(userCredentials.getEmail(), userCredentials.getPassword());
    }

    @PostMapping("/login")
    public void login(@RequestBody UserCredentialsDTO userCredentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
