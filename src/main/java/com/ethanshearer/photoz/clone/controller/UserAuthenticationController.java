package com.ethanshearer.photoz.clone.controller;

import com.ethanshearer.photoz.clone.dto.UserCredentialsDTO;
import com.ethanshearer.photoz.clone.entities.AuthTokenEntity;
import com.ethanshearer.photoz.clone.exceptions.UserAlreadyExistsException;
import com.ethanshearer.photoz.clone.model.AuthToken;
import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.repository.AuthTokenRepositoy;
import com.ethanshearer.photoz.clone.service.AuthTokenService;
import com.ethanshearer.photoz.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthenticationController {
    @Autowired private UserService userService;
    @Autowired private AuthTokenRepositoy authTokenRepository;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private AuthTokenService authTokenService;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserCredentialsDTO userCredentials) throws UserAlreadyExistsException {
        userService.registerUser(userCredentials.getEmail(), userCredentials.getPassword());
    }

    @PostMapping("/login")
    public AuthTokenEntity login(@RequestBody UserCredentialsDTO userCredentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentials.getEmail(), userCredentials.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) userService.loadUserByUsername(userCredentials.getEmail());
        AuthToken authToken = authTokenService.generateAuthToken(user);
        AuthTokenEntity tokenEntity = new AuthTokenEntity(authToken);
        return tokenEntity;
    }
}
