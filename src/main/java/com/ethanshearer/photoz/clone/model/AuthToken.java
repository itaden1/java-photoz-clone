package com.ethanshearer.photoz.clone.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("AUTH_TOKENS")
public class AuthToken {

    @Id private int id;
    @NotEmpty private int userId;

    @Column("TOKEN")
    @NotEmpty
    private UUID token;

    @NotEmpty private LocalDateTime createdDateTime;

    @NotEmpty private LocalDateTime tokenExpiry;

    @NotEmpty private UUID refreshToken;

    public AuthToken(int userId) {
        this.userId = userId;
        this.token = UUID.randomUUID();
        this.createdDateTime = LocalDateTime.now();
        this.tokenExpiry = LocalDateTime.now().plusHours(4);
        this.refreshToken = UUID.randomUUID();
        this.refreshTokenExpiry = LocalDateTime.now().plusHours(48);
    }

    public int getId() {
        return id;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
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

    public UUID getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(UUID refreshToken) {
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
