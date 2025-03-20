package com.mohit.userServices.consumer;

import com.mohit.userServices.entities.UserInfo;
import com.mohit.userServices.entities.UserInfoDto;
import com.mohit.userServices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mohit.userServices.repository.UserRepository;


@Service
public class UserInfoConsumer {

     @Autowired private UserService userService;
      
      
     
 

    @KafkaListener(topics = "${spring.kafka.topic-name}",groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDto eventdata){
        System.out.println("consumes "+eventdata.toString());

        try{
            System.out.println("consuming the data");
            userService.createOrUpdateUser(eventdata);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    
}
