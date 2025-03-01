package com.mohit.userServices.deserializer;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.userServices.entities.UserInfo;

public class UserDeserializer implements Deserializer<UserInfo> {

    @Override
    public UserInfo deserialize(String arg0, byte[] arg1) {
       UserInfo userInfo=null;

       ObjectMapper objectMapper=new ObjectMapper();
       try{
        userInfo=objectMapper.readValue(arg1, UserInfo.class);
           System.out.println(userInfo);
       }
       catch(Exception e){
        e.printStackTrace();
       }

       return userInfo;

    }
    
}
