package com.ethanshearer.photoz.clone.controller;

import com.ethanshearer.photoz.clone.dto.FollowRequestDTO;
import com.ethanshearer.photoz.clone.exceptions.EntityNotFoundException;
import com.ethanshearer.photoz.clone.model.Follow;
import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.service.FollowService;
import com.ethanshearer.photoz.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {

    @Autowired FollowService followService;
    @PostMapping("/api/users/follow/")
    public Follow createFollowRequest(@RequestBody FollowRequestDTO followRequestDTO) throws EntityNotFoundException {
        Follow followRequest = followService.createFollowRequest(followRequestDTO.getUserId());
        return followRequest;
    }

//    @PatchMapping("/api/users/follow/{}")
//    public Follow updateFollowRequest(@RequestBody FollowDTO, followDTO) {
//
//    }
}
