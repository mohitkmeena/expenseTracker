package com.mohit.expensetracker.auth.eventProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.mohit.expensetracker.auth.dto.UserDto;




@Service
public class UserProducer {

    @Value("${spring.kafka.topic-name}")
    private String TOPIC_NAME; 

    private final KafkaTemplate<String,UserEventInfo> template;
   @Autowired
   public UserProducer(KafkaTemplate<String ,UserEventInfo> template){
      this.template=template;
    }

    public void sendUsertoSave(UserEventInfo userDto){
        Message<UserEventInfo>message=MessageBuilder.withPayload(userDto).setHeader(KafkaHeaders.TOPIC, TOPIC_NAME).build();
       template.send(message);
        System.out.println("user sent ");

    }

}
