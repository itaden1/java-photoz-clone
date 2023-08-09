package com.ethanshearer.photoz.clone.controller;

import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired UserService userService;
    @GetMapping("/api/users/")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }
}
