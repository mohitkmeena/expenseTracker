package com.mohit.expensetracker.auth.config;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired JwtFilter jwtFilter;
    @Autowired AuthenticationProvider authenticationProvider;
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
   

    return httpSecurity.csrf(customizer->customizer.disable())
    .authorizeRequests(auth->auth
                         .requestMatchers("/api/v1/auth/**").permitAll()
                         .requestMatchers("/api/v1/token/**").permitAll()
                         .requestMatchers("/api/v1/password/**").permitAll()
                        .anyRequest().authenticated())
    .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    .build();
   }



}
