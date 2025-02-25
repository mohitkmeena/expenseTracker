package com.mohit.expensetracker.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mohit.expensetracker.auth.entity.User;
import com.mohit.expensetracker.auth.entity.UserRole;
@Service
public class CustomUserDetails extends User implements UserDetails {
    private final String username; 
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;
   
    public CustomUserDetails(User user){
        this.username=user.getUsername();
        this.password=user.getPassword();
        List<GrantedAuthority>auths=new ArrayList<>();
        for(UserRole role:user.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getRole().toUpperCase()));
        }
        this.authorities=auths;
    }

    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username; 
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
}
