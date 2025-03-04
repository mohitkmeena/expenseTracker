package com.mohit.userServices.controller;

import com.mohit.userServices.entities.UserInfoDto;
import com.mohit.userServices.service.UserService;
import lombok.Getter;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserContoller {
    @Autowired private UserService userService;
   @PostMapping("/createUpdate")
    public ResponseEntity<UserInfoDto> createUpdate(@RequestBody UserInfoDto userInfoDto){

       try {
          UserInfoDto userInfoDto1= userService.createOrUpdateUser(userInfoDto);
           return new ResponseEntity<>(userInfoDto1, HttpStatus.ACCEPTED);
       }
       catch (Exception ex){
           ex.printStackTrace();
           return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
       }

   }
   @GetMapping("getUser")
    public  ResponseEntity<UserInfoDto> getUser(@RequestBody UserInfoDto userInfoDto){
       try {
           UserInfoDto userInfoDto1= userService.getUser(userInfoDto);
           return new ResponseEntity<>(userInfoDto1, HttpStatus.OK);
       }
       catch (Exception ex){
           ex.printStackTrace();
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }
}
