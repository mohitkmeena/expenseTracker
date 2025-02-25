package com.mohit.expensetracker.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.expensetracker.auth.dto.LoginReq;
import com.mohit.expensetracker.auth.dto.RefreshTokenDto;
import com.mohit.expensetracker.auth.dto.ResponseDto;
import com.mohit.expensetracker.auth.dto.UserDto;
import com.mohit.expensetracker.auth.entity.RefreshToken;
import com.mohit.expensetracker.auth.entity.User;
import com.mohit.expensetracker.auth.service.CustomUserDetails;
import com.mohit.expensetracker.auth.service.JwtService;
import com.mohit.expensetracker.auth.service.RefreshTokenService;
import com.mohit.expensetracker.auth.service.UserDetailsImpl;
import com.mohit.expensetracker.exceptions.TokenNotFoundException;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthContoller {
    
    @Autowired
    private UserDetailsImpl userDetailsImpl;
    @Autowired 
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JwtService jService;
    @Autowired
    private AuthenticationManager authenticationManager;

    
    
    @PostMapping("/signup")
    public ResponseEntity signup(UserDto userDto){
        boolean signedup= userDetailsImpl.signup(userDto);
        if(!signedup){
           return new ResponseEntity<>("already exist", HttpStatus.BAD_REQUEST);
        }
        RefreshToken rtoken= refreshTokenService.createRefreshToken(userDto.getUsername());
        String token=jService.generateToken(new CustomUserDetails(userDto));

        return new ResponseEntity<>(ResponseDto
        .builder()
        .responseMessage(token+ "  refresh token "+ rtoken)
        .responsecode("created successfully")
        .build(),
          HttpStatus.ACCEPTED
        );
    }
    @PostMapping("/login")
    public ResponseEntity login(LoginReq loginReq){
        Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        if(auth.isAuthenticated()){
            RefreshToken refreshToken=refreshTokenService.createRefreshToken(loginReq.getUsername());
            String token=jService.generateToken(new CustomUserDetails(loginReq));
            return new ResponseEntity<>(ResponseDto
            .builder()
            .responseMessage(token+ "  refresh token "+ refreshToken)
            .responsecode("created successfully")
            .build(),
              HttpStatus.ACCEPTED
            );
        }
        else return new ResponseEntity<>("error in login ",HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @PostMapping("auth/v1/refreshToken")
    public ResponseDto refreshToken(@RequestBody RefreshTokenDto refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::validaRefreshToken)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jService.generateToken(new CustomUserDetails(user));
                    return ResponseDto.builder()
                            .responseMessage(accessToken+" "+refreshTokenRequestDTO.getToken())
                            .build();
                }).orElseThrow(() ->new TokenNotFoundException("Refresh Token is not in DB..!!"));
    }
   
}
