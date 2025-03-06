package com.mohit.expenseservice.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.expenseservice.dto.ExpenseDto;
import com.mohit.expenseservice.entity.Expense;
import org.apache.kafka.common.serialization.Deserializer;

public class ExpenseDeserializer implements Deserializer<ExpenseDto> {


    @Override
    public ExpenseDto deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper=new ObjectMapper();
        ExpenseDto expense=null;
        try{
            expense=objectMapper.readValue(data,ExpenseDto.class);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return expense;
    }
}
