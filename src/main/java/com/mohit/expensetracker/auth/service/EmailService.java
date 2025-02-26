package com.mohit.expensetracker.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mohit.expensetracker.auth.dto.EmailDto;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    
  @Value("${spring.mail.username}")
  private String sender;
    public void sendMail(EmailDto mailBody){
          SimpleMailMessage mailMessage=new SimpleMailMessage();
          mailMessage.setTo(mailBody.to());
          mailMessage.setFrom(sender);
          mailMessage.setSubject(mailBody.sub());
          mailMessage.setText(mailBody.message());
          javaMailSender.send(mailMessage);
    }
}