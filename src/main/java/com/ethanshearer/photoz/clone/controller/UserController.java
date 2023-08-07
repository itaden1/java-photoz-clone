package com.ethanshearer.photoz.clone.controller;

import com.ethanshearer.photoz.clone.dto.FollowRequestDTO;
import com.ethanshearer.photoz.clone.exceptions.EntityNotFoundException;
import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {

    @Autowired UserService userService;
    @PostMapping("/api/users/follow/{user_id}")
    public User follow(@RequestBody FollowRequestDTO followRequestDTO) throws EntityNotFoundException {
        // TODO follow logic
        return userService.getUserById(followRequestDTO.getUserId());
    }


}
