package com.mohit.expensetracker.auth.service;

import java.util.HashSet;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mohit.expensetracker.auth.dto.UserDto;
import com.mohit.expensetracker.auth.entity.User;
import com.mohit.expensetracker.auth.repository.UserRepository;
import com.mohit.expensetracker.exceptions.UserNotFoundException;

public class UserDetailsImpl implements UserDetailsService  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
             User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("user not found with this username "+username));
             return new CustomUserDetails(user);
    }
    
    private User checkIfUserAlreadyExist(UserDto userDto){
        return userRepository.findByUsername(userDto.getUsername()).get();
    }
    public boolean signup(UserDto userDto){
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if(Objects.nonNull(checkIfUserAlreadyExist(userDto))){
            return false;
        }

        User user=User.builder()
        .username(userDto.getUsername())
         .password(userDto.getPassword())
         .email(userDto.getEmail())
         .roles(new HashSet<>())
         .build();
         userRepository.save(user);


         return true;
    }
}
