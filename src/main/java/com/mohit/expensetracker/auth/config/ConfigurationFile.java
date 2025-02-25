package com.mohit.expensetracker.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mohit.expensetracker.auth.service.UserDetailsImpl;
@Configuration
public class ConfigurationFile {
    
    @Bean
    public PasswordEncoder passwordEncoder(){
       return  new BCryptPasswordEncoder();
    }
   @Autowired UserDetailsImpl userDetailsService;
    
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authp=new DaoAuthenticationProvider();
        authp.setUserDetailsService(userDetailsService);
        authp.setPasswordEncoder(passwordEncoder());
        return authp;

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return  config.getAuthenticationManager();
    }


}
