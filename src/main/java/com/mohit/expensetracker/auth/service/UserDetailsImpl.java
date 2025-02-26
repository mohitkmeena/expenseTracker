package com.mohit.expensetracker.auth.service;

import java.util.HashSet;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mohit.expensetracker.auth.dto.UserDto;
import com.mohit.expensetracker.auth.entity.User;
import com.mohit.expensetracker.auth.repository.UserRepository;
import com.mohit.expensetracker.exceptions.UserNotFoundException;
@Service
public class UserDetailsImpl implements UserDetailsService  {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationContext context;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
             User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("user not found with this username "+username));
             return new CustomUserDetails(user);
    }
    
    private boolean checkIfUserAlreadyExist(UserDto userDto){
        return userRepository.existsByUsername(userDto.getUsername());
    }
    public boolean signup(UserDto userDto){
        userDto.setPassword(context.getBean(PasswordEncoder.class).encode(userDto.getPassword()));
        if(checkIfUserAlreadyExist(userDto)){
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
