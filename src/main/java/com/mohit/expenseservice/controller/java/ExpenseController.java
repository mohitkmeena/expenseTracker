package com.mohit.expenseservice.controller.java;

import com.mohit.expenseservice.dto.ExpenseDto;
import com.mohit.expenseservice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/expenses")
public class ExpenseController {
    @Autowired private ExpenseService expenseService;

    @GetMapping("/expenses")
    public ResponseEntity<List<ExpenseDto> >getAllExpenses(@RequestParam("userId") String userId){
        try {
            List<ExpenseDto> expenseDtos=expenseService.getAllExp(userId);
            return new ResponseEntity<>(expenseDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping("/expenses")
    public ResponseEntity<Boolean>createExpenses(@RequestParam ("userId") String userId,@RequestBody ExpenseDto expenseDto){
        try {
            expenseDto.setUserId(userId);

                return new ResponseEntity<>(expenseService.createExpense(expenseDto),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.NOT_MODIFIED);
        }


    }
}
