package com.mohit.expensetracker.exceptions;

public class TokenNotFoundException extends RuntimeException {
     public TokenNotFoundException(String message){
        super(message);
     }
}
