package com.mohit.expensetracker.auth.dto;

import lombok.Builder;

@Builder
public record EmailDto(String to,String sub,String message) {
    
}
