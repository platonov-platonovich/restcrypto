package com.tolya.cryptocurrencies.security;

import com.tolya.cryptocurrencies.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
@Component
@AllArgsConstructor
public class AuthProviderImp implements AuthenticationProvider {
    private final UserService userService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
UserDetails userDetails = userService.loadUserByUsername(username);
    String password = authentication.getCredentials().toString();
   if (!password.equals(userDetails.getPassword()))
    throw new BadCredentialsException("Incorrect password");
    return new UsernamePasswordAuthenticationToken(userDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
