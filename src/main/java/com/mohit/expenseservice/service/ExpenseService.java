package com.mohit.expenseservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.expenseservice.dto.ExpenseDto;
import com.mohit.expenseservice.entity.Expense;
import com.mohit.expenseservice.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    @Autowired private ExpenseRepository expenseRepository;
private ObjectMapper objectMapper=new ObjectMapper();
     public boolean createExpense(ExpenseDto expenseDto){
          setCurrency(expenseDto);
          try{
              expenseRepository.save(objectMapper.convertValue(expenseDto, Expense.class));
              return true;
          }catch (Exception e){
              e.printStackTrace();
              return false;
          }
     }
     public boolean update(ExpenseDto expenseDto){
         Optional<Expense> expense=expenseRepository.findByUserIdAndExternalId(expenseDto.getUserId(),expenseDto.getExternalId());
         if(expense.isEmpty()) return  false;
         Expense expense1=expense.get();
         if(expenseDto.getAmount()!=null)expense1.setAmount(expenseDto.getAmount());
         if(expenseDto.getCurrency()!=null)expense1.setCurrency(expenseDto.getCurrency());
         if(expenseDto.getMerchant()!=null)expense1.setMerchant(expenseDto.getMerchant());

         expenseRepository.save(expense1);
         return true;
     }
     private void setCurrency(ExpenseDto expenseDto){
         expenseDto.setCurrency("INR");
     }

     public List<ExpenseDto> getAllExp(String userId){
         List<Expense> expenses=expenseRepository.findByUserId(userId);
         return objectMapper.convertValue(expenses, new TypeReference<List<ExpenseDto>>(){});
     }

}
