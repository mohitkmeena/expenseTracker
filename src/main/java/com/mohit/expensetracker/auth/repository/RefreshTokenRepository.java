package com.mohit.expensetracker.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.expensetracker.auth.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    

    Optional<RefreshToken>findByToken(String token);
    
}
