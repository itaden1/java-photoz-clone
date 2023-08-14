package com.ethanshearer.photoz.clone.controller;

import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired UserService userService;
    @GetMapping("/api/users/")
    @JsonView(Views.Public.class)
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }
}
