package com.mohit.expensetracker.auth.service;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mohit.expensetracker.auth.dto.EmailDto;
import com.mohit.expensetracker.auth.entity.ForgetPassword;
import com.mohit.expensetracker.auth.entity.User;
import com.mohit.expensetracker.auth.repository.ForgetPasswordRepository;
import com.mohit.expensetracker.auth.repository.UserRepository;
import com.mohit.expensetracker.exceptions.ForgetPasswordReqException;
import com.mohit.expensetracker.exceptions.UserNotFoundException;



@Component
public class ForgetPasswordSecvice {
    /*
     * verify email;
     * sent otp;
     * enter otp;
     * give password
     * verify password
     * update passowrd;
     * 
     */
    @Autowired private UserRepository userRepository;
    @Autowired private EmailService emailService;
    @Autowired private ForgetPasswordRepository forgetPasswordRepository;
    @Autowired private PasswordEncoder passwordEncoder;

     public String verifyEmailAndSendOtp(String email){
    User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("enter correct email"));
        Integer otp=otpGeneration();
        EmailDto mailBody=EmailDto.builder()
        .to(email)
        .sub("otp verification")
        .message("otp to reset the password "+otp)
        .build();

        ForgetPassword forgetPassword=ForgetPassword.builder()
        .user(user)
        .otp(otp)
        .expirationTime(new Date(System.currentTimeMillis()+10*60*1000))
        .build();
        emailService.sendMail(mailBody);

        forgetPasswordRepository.save(forgetPassword);
        return "email sent successfully";
     }

     private Integer otpGeneration(){
        Random random=new Random();
        return random.nextInt(000_000, 999_999);
     }

     public String verifyOtp(Integer otp,String email){
        User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("user not found"));
        ForgetPassword forgetPassword=forgetPasswordRepository.findByOtpAndUser(otp, user).orElseThrow(()-> new ForgetPasswordReqException("request not found"));
        if(forgetPassword.getExpirationTime().before(Date.from(Instant.now()))){
            
            forgetPasswordRepository.delete(forgetPassword);
                return "Otp expired";
        }
        return "OTP VERIFIED";
     }
     @Transactional
     public String updatePassword(String email,String password,String rpassword){
        if(!Objects.equals(password, rpassword)){
                return"password not matching";
        }
        password=passwordEncoder.encode(rpassword);

        User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("user not found"));
        userRepository.updatePassword(user, password);
        ForgetPassword forgetPassword=forgetPasswordRepository.findByUser(user);
        forgetPasswordRepository.delete(forgetPassword);
        return "updated successful";
     }
}
