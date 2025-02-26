package com.mohit.expensetracker.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.expensetracker.auth.dto.ResetPasswordDto;
import com.mohit.expensetracker.auth.dto.VerifyOtp;
import com.mohit.expensetracker.auth.service.ForgetPasswordSecvice;

@RestController
@RequestMapping("/api/v1/password")
public class PassswordController {
@Autowired
private ForgetPasswordSecvice forgetPasswordSecvice;

   @PostMapping("/verify")
   public String verifyEmail(@RequestParam String email){
        return forgetPasswordSecvice.verifyEmailAndSendOtp(email);
   }
   @PostMapping("/verify-otp")
   public String veriifyOtp(@RequestBody VerifyOtp verifyOtp ){

        return  forgetPasswordSecvice.verifyOtp(verifyOtp.getOtp(), verifyOtp.getEmail());
   }
   @PutMapping("/update-password")
   
   public String updatePassword(@RequestBody ResetPasswordDto resetPasswordDto){

        return  forgetPasswordSecvice.updatePassword(resetPasswordDto.getEmail(),resetPasswordDto.getPassword(), resetPasswordDto.getRpassword());
   }



    
}
