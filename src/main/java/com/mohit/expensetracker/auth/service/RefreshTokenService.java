package com.mohit.expensetracker.auth.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mohit.expensetracker.auth.entity.RefreshToken;
import com.mohit.expensetracker.auth.entity.User;
import com.mohit.expensetracker.auth.repository.RefreshTokenRepository;
import com.mohit.expensetracker.auth.repository.UserRepository;
import com.mohit.expensetracker.exceptions.TokenExpiredException;
import com.mohit.expensetracker.exceptions.UserNotFoundException;

@Service
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${app.refresh-token-expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;

    public RefreshToken createRefreshToken(String username){
        User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("user not found with given username "+username ));
       RefreshToken refreshToken=  RefreshToken .builder()
       .user(user)
       .expirationtime(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION))
       .token(UUID.randomUUID().toString())
       .build();
      refreshTokenRepository.save(refreshToken);
      return refreshToken;
    }

    public RefreshToken validaRefreshToken(RefreshToken token){
           if(token.getExpirationtime().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new TokenExpiredException("token expired so login again");
           }
           return token;

    }
    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }
}
