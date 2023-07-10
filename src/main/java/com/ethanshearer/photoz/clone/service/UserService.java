package com.ethanshearer.photoz.clone.service;

import com.ethanshearer.photoz.clone.exceptions.EntityNotFoundException;
import com.ethanshearer.photoz.clone.model.User;
import com.ethanshearer.photoz.clone.repository.UserRepository;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) throw new EntityNotFoundException();
        return user;
    }

    public void registerUser(String email, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(email, encodedPassword);
        userRepository.save(user);
    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        return user;
    }
}
