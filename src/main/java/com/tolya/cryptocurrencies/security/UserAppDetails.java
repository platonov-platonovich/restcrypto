package com.tolya.cryptocurrencies.security;

import com.tolya.cryptocurrencies.models.UserApp;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@AllArgsConstructor
public class UserAppDetails implements org.springframework.security.core.userdetails.UserDetails {
   private final UserApp userApp;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.userApp.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userApp.getUsername();
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
    public UserApp userApp(){
        return this.userApp;
    }
}
