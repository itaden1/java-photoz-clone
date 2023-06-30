package com.ethanshearer.photoz.clone.service;

import com.ethanshearer.photoz.clone.model.AuthToken;
import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.repository.AuthTokenRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {
    @Autowired
    private AuthTokenRepositoy authTokenRespository;

    public AuthToken generateAuthToken(User user) {
        AuthToken token = new AuthToken(user.getId());
        authTokenRespository.save(token);
        return token;
    }

}
