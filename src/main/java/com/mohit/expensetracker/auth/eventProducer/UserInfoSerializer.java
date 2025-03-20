package com.mohit.expensetracker.auth.eventProducer;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.expensetracker.auth.dto.UserDto;

public class UserInfoSerializer implements Serializer<UserEventInfo> {

    @Override
    public byte[] serialize(String arg0, UserEventInfo userDto) {
        byte [] retval=null;
        ObjectMapper objectMapper=new ObjectMapper();
        try{
            retval=objectMapper.writeValueAsString(userDto).getBytes();
        }
        catch(Exception e){
            e.printStackTrace();
        }
       return retval;
    }
    
}
