package com.ethanshearer.photoz.clone.service;

import com.ethanshearer.photoz.clone.model.Follow;
import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.repository.FollowRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FollowService {
    @Autowired FollowRepository followRepository;
    @Autowired UserService userService;

    public Follow createFollowRequest(int userID) throws com.ethanshearer.photoz.clone.exceptions.EntityNotFoundException {
        User follower = userService.getLoggedInUser();
        User userToFollow = userService.getUserById(userID);
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(userToFollow);
        follow.setAccepted(false);
        follow.setCreated(LocalDateTime.now());
        followRepository.save(follow);
        return follow;
    }

    public Follow acceptFollowRequest(int followId) {
        Follow follow = followRepository.findById(followId).orElse(null);
        if (follow == null) throw new EntityNotFoundException();
        follow.setAccepted(true);
        followRepository.save(follow);
        return follow;
    }
}
