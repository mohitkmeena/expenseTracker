package com.mohit.userServices.deserializer;

import com.mohit.userServices.entities.UserInfoDto;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.userServices.entities.UserInfoDto;

public class UserDeserializer implements Deserializer<UserInfoDto> {

    @Override
    public UserInfoDto deserialize(String arg0, byte[] arg1) {
       UserInfoDto userInfo=null;

       ObjectMapper objectMapper=new ObjectMapper();
       try{
        userInfo=objectMapper.readValue(arg1, UserInfoDto.class);

       }
       catch(Exception e){
        e.printStackTrace();
       }

       return userInfo;

    }
    
}
