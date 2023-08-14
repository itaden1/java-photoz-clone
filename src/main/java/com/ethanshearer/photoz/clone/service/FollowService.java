package com.ethanshearer.photoz.clone.service;

import com.ethanshearer.photoz.clone.exceptions.PermissionDeniedException;
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
        // TODO check for existing follow requests
        Follow follow = new Follow();
        follow.setFollower(follower.getId());
        follow.setFollowing(userToFollow.getId());
        follow.setAccepted(false);
        follow.setCreated(LocalDateTime.now());
        followRepository.save(follow);
        return follow;
    }

    public Follow acceptFollowRequest(int followId) throws PermissionDeniedException, EntityNotFoundException {
        Follow follow = followRepository.findById(followId).orElse(null);
        User principle = userService.getLoggedInUser();
        if (follow == null) {
            throw new EntityNotFoundException();
        }
        if (follow.getFollower() == principle.getId()) {
            throw new PermissionDeniedException();
        }
        follow.setAccepted(true);
        followRepository.save(follow);
        return follow;
    }

    public Follow declineFollowRequest(int followId) {
        Follow follow = followRepository.findById(followId).orElse(null);
        if (follow == null) throw new EntityNotFoundException();
        follow.setAccepted(false);
        follow.setDeclined(true);
        followRepository.save(follow);
        return follow;
    }

    public Iterable<Follow> getFollowRequests() {
        User principle = userService.getLoggedInUser();
        return followRepository.findAllByFollowerIDOrFollowingID(principle.getId(), principle.getId());
    }
}
