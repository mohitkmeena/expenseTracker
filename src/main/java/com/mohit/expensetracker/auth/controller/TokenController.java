package com.mohit.expensetracker.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.expensetracker.auth.dto.RefreshTokenDto;
import com.mohit.expensetracker.auth.dto.ResponseDto;
import com.mohit.expensetracker.auth.entity.RefreshToken;
import com.mohit.expensetracker.auth.service.JwtService;
import com.mohit.expensetracker.auth.service.RefreshTokenService;
import com.mohit.expensetracker.exceptions.TokenNotFoundException;

@RestController
@RequestMapping("/api/v1/token/")
public class TokenController {
    @Autowired 
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JwtService jService;
    @PostMapping("/refreshToken")
    public ResponseDto refreshToken(@RequestBody RefreshTokenDto refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::validaRefreshToken)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jService.generateToken(user.getUsername());
                    return ResponseDto.builder()
                            .responseMessage(accessToken+" "+refreshTokenRequestDTO.getToken())
                            .build();
                }).orElseThrow(() ->new TokenNotFoundException("Refresh Token is not in DB..!!"));
    }
}
