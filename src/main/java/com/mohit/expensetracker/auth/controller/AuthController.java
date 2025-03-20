package com.mohit.expensetracker.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mohit.expensetracker.auth.dto.LoginReq;
import com.mohit.expensetracker.auth.dto.ResponseDto;
import com.mohit.expensetracker.auth.dto.UserDto;
import com.mohit.expensetracker.auth.entity.RefreshToken;
import com.mohit.expensetracker.auth.service.JwtService;
import com.mohit.expensetracker.auth.service.RefreshTokenService;
import com.mohit.expensetracker.auth.service.UserDetailsImpl;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    
    @Autowired
    private UserDetailsImpl userDetailsImpl;
    @Autowired 
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JwtService jService;
    @Autowired
    private AuthenticationManager authenticationManager;

    
    
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody UserDto userDto){
        
        boolean signedup= userDetailsImpl.signup(userDto);
        if(!signedup){
           return new ResponseEntity<>(ResponseDto
             .builder()
           .responseMessage("user already exist,please try login")
           .responsecode("ALREADY_EXIST")
           .build(), HttpStatus.BAD_REQUEST);
        }
        RefreshToken rtoken= refreshTokenService.createRefreshToken(userDto.getUsername());
        String token=jService.generateToken(userDto.getUsername());

        return new ResponseEntity<>(ResponseDto
        .builder()
        .responseMessage(token+ "  refresh token "+ rtoken.getToken())
        .responsecode("created successfully")
        .build(),
          HttpStatus.ACCEPTED
        );
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login( @RequestBody LoginReq loginReq){
        
        Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        
        if(auth.isAuthenticated()){
            RefreshToken refreshToken=refreshTokenService.createRefreshToken(loginReq.getUsername());
            String token=jService.generateToken(loginReq.getUsername());
            return new ResponseEntity<>(ResponseDto
            .builder()
            .responseMessage(token+ "  refresh token "+refreshToken.getToken())
            .responsecode("created successfully")
            .build(),
              HttpStatus.ACCEPTED
            );
        }
        
        else return new ResponseEntity<>(ResponseDto
        .builder()
        .responseMessage( " error in token creation")
        .responsecode("NOT_CREATED")
        .build()
        ,HttpStatus.INTERNAL_SERVER_ERROR);
        
    }

     
}
