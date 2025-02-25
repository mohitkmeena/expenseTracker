package com.mohit.expensetracker.exceptions;

public class TokenExpiredException extends RuntimeException {
    
    public TokenExpiredException(String message){
        super(message);
    }
}
