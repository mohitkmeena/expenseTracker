package com.mohit.expensetracker.auth.dto;


import com.mohit.expensetracker.auth.entity.User;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class LoginReq extends User {
    private String username;
    private String password;
}
