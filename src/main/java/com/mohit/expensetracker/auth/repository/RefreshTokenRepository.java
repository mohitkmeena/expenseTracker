package com.mohit.expensetracker.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mohit.expensetracker.auth.entity.RefreshToken;
import com.mohit.expensetracker.auth.entity.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    

    Optional<RefreshToken>findByToken(String token);

    @Query("SELECT rt FROM RefreshToken rt WHERE rt.user = ?1")
    RefreshToken getByUser(User user);
    
}
