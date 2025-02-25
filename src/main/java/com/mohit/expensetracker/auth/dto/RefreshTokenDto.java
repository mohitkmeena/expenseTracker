package com.mohit.expensetracker.auth.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@Getter
public class RefreshTokenDto {
    private String token;
    
}
