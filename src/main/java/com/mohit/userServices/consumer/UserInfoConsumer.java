package com.mohit.userServices.consumer;

import com.mohit.userServices.entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mohit.userServices.repository.UserRepository;


@Service
public class UserInfoConsumer {

     private UserRepository userRepository;
      
      
     @Autowired
     public UserInfoConsumer(UserRepository userRepository){
        this.userRepository=userRepository;
     }
 

    @KafkaListener(topics = "${spring.kafka.topic-name}",groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfo eventdata){
        System.out.println("consumes "+eventdata.toString());

        try{
        userRepository.save(eventdata);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    
}
