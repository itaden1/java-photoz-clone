package com.ethanshearer.photoz.clone.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("FOLLOW")
public class Follow {
    @Id private Integer id;
    @NotEmpty private boolean accepted;
    public boolean isDeclined() {
        return declined;
    }

    public void setDeclined(boolean declined) {
        this.declined = declined;
    }

    @NotEmpty private boolean declined;
    private LocalDateTime created;
    private LocalDateTime acceptedDateTime;

    private int followerID;

    private int followingID;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        if (accepted == true) {
            this.acceptedDateTime = LocalDateTime.now();
        }
        this.accepted = accepted;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getAcceptedDatTime() {
        return acceptedDateTime;
    }

    public void setAcceptedDatTime(LocalDateTime acceptedDatTime) {
        this.acceptedDateTime = acceptedDatTime;
    }

    public int getFollower() {
        return followerID;
    }

    public void setFollower(int followerID) {
        this.followerID = followerID;
    }

    public int getFollowing() {
        return followingID;
    }

    public void setFollowing(int followingID) {
        this.followingID = followingID;
    }

}
