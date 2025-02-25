package com.mohit.expensetracker.auth.dto;

import com.mohit.expensetracker.auth.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter

public class UserDto extends User {
    private String usrname;
    private String password;
    private String email;
}
