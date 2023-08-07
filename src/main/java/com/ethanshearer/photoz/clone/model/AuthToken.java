package com.ethanshearer.photoz.clone.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Table("AUTH_TOKENS")
public class AuthToken {

    @Id private int id;

    public int getUserId() {
        return userId;
    }

    @NotEmpty private int userId;

    @Column("TOKEN")
    @NotEmpty
    private String token;

    @NotEmpty private LocalDateTime createdDateTime;

    @NotEmpty private LocalDateTime tokenExpiry;

    @NotEmpty private String refreshToken;

    public AuthToken(int userId) {
        this.userId = userId;
        this.token = generateToken();
        this.createdDateTime = LocalDateTime.now();
        this.tokenExpiry = LocalDateTime.now().plusHours(4);
        this.refreshToken = generateToken();
        this.refreshTokenExpiry = LocalDateTime.now().plusHours(48);
    }

    private String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
    public int getId() {
        return id;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(LocalDateTime tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LocalDateTime getRefreshTokenExpiry() {
        return refreshTokenExpiry;
    }

    public void setRefreshTokenExpiry(LocalDateTime refreshTokenExpiry) {
        this.refreshTokenExpiry = refreshTokenExpiry;
    }

    @NotEmpty private LocalDateTime refreshTokenExpiry;

}
