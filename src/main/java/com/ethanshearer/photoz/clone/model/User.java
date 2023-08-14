package com.ethanshearer.photoz.clone.model;

import com.ethanshearer.photoz.clone.controller.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Table("USERS")
public class User implements UserDetails {
    @JsonView(Views.Public.class)
    @Id
    private Integer id;

    @NotEmpty
    @Column("EMAIL_ADDRESS")
    private String email;

    @NotEmpty private String password;

//    @OneToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Set<Follow> followRequests;

//    @OneToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL)
//    private Set<Follow> follows;

    public User(String email, String password) {
        this.setPassword(password);
        this.setEmail(email);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

