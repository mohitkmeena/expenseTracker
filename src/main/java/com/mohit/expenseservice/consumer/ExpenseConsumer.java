package com.mohit.expenseservice.consumer;

import com.mohit.expenseservice.dto.ExpenseDto;
import com.mohit.expenseservice.entity.Expense;
import com.mohit.expenseservice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExpenseConsumer {
      private ExpenseService expenseService;
      @Autowired
      public  ExpenseConsumer(ExpenseService expenseService){
          this.expenseService=expenseService;
      }
    @KafkaListener(topics = "${spring.kafka.topic-name}",groupId = "${spring.kafka.consumer.group-id}")
    public void  expenseListner(ExpenseDto expense){
        System.out.println(expense.toString());
        try{
            expenseService.createExpense(expense);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
