package com.ethanshearer.photoz.clone.controller;

import com.ethanshearer.photoz.clone.dto.FollowRequestDTO;
import com.ethanshearer.photoz.clone.dto.FollowUpdateDTO;
import com.ethanshearer.photoz.clone.exceptions.EntityNotFoundException;
import com.ethanshearer.photoz.clone.exceptions.PermissionDeniedException;
import com.ethanshearer.photoz.clone.model.Follow;
import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.service.FollowService;
import com.ethanshearer.photoz.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FollowController {

    @Autowired FollowService followService;
    @PostMapping("/api/users/follow/")
    public Follow createFollowRequest(@RequestBody FollowRequestDTO followRequestDTO) throws EntityNotFoundException {
        Follow followRequest = followService.createFollowRequest(followRequestDTO.getUserId());
        return followRequest;
    }

    @GetMapping("/api/users/follow/")
    public Iterable<Follow> getFollowRequests() {
        return followService.getFollowRequests();
    }

    @PatchMapping("/api/users/follow/{followId}")
    public Follow updateFollowRequest(@PathVariable Integer followId, @RequestBody FollowUpdateDTO followUpdate) throws PermissionDeniedException {
        Follow follow;
        if (followUpdate.isAccept()) {
            follow = followService.acceptFollowRequest(followId);
        } else {
            follow = followService.declineFollowRequest(followId);
        }
        return follow;
    }
}
