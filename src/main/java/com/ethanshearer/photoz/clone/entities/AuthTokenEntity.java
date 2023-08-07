package com.ethanshearer.photoz.clone.entities;

import com.ethanshearer.photoz.clone.model.AuthToken;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuthTokenEntity {
    private String token;
    private LocalDateTime token_expiry;
    private String refresh_token;

    public AuthTokenEntity(AuthToken authToken) {
        this.token = authToken.getToken();
        this.token_expiry = authToken.getTokenExpiry();
        this.refresh_token = authToken.getRefreshToken();
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getToken_expiry() {
        return token_expiry;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

}
