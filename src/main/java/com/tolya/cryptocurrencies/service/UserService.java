package com.tolya.cryptocurrencies.service;

import com.tolya.cryptocurrencies.models.UserApp;
import com.tolya.cryptocurrencies.repositories.UserRepo;
import com.tolya.cryptocurrencies.security.UserAppDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public  class UserService implements UserDetailsService {
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional <UserApp> userApp = userRepo.findByUsername(username);
    if(userApp.isEmpty())
        throw  new UsernameNotFoundException("User not found");
    return  new UserAppDetails(userApp.get());
    }
}

