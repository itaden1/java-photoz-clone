package com.ethanshearer.photoz.clone.security;

import com.ethanshearer.photoz.clone.model.AuthToken;
import com.ethanshearer.photoz.clone.repository.AuthTokenRepositoy;
import com.ethanshearer.photoz.clone.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class CustomTokenFilter extends OncePerRequestFilter {

    @Autowired
    private AuthTokenRepositoy tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get token from request
        UUID uuidToken;
        String requestToken = request.getHeader("Authorization");

        if (requestToken == null) {
            filterChain.doFilter(request, response);
            return;
        }
        String[] tokenParts = requestToken.split(" ");
        uuidToken = UUID.fromString(tokenParts[1]);

        AuthToken token = tokenRepository.findByToken(uuidToken);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        LocalDateTime time = LocalDateTime.now();
        // check if it is valid
        if (token.getTokenExpiry().isBefore(LocalDateTime.now())) {
            System.out.println("oops");
            filterChain.doFilter(request, response);
            return;
        }

        // get the user associated with the token and authenticate them
        UserDetails user = userRepository.findById(token.getUserId()).orElse(null);
        UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken
                .authenticated(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
