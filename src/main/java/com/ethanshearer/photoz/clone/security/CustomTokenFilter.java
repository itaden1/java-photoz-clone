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
import java.time.LocalDateTime;
import java.util.List;

@Component
public class CustomTokenFilter extends OncePerRequestFilter {

    @Autowired
    private AuthTokenRepositoy tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get token from request
        String requestToken = request.getHeader("Authorization");
        if (requestToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        AuthToken token = tokenRepository.getTokenByToken(requestToken);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // check if it is valid
        if (token.getTokenExpiry().isAfter(LocalDateTime.now())) {
            System.out.println("oops");
            filterChain.doFilter(request, response);
            return;
        }

        // get the user associated with the token and authenticate them
        UserDetails user = userRepository.findById(token.getId()).orElse(null);
        UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken
                .authenticated(user, null, List.of((GrantedAuthority) user.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
