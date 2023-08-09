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
    private LocalDateTime created;
    private LocalDateTime acceptedDateTime;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private User follower;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private User following;
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
        this.acceptedDateTime = LocalDateTime.now();
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

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

}
