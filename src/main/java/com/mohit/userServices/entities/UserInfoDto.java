package com.mohit.userServices.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserInfoDto {

    @Id
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("profile_pic")
    private String profilePic;


    public  UserInfo transferToUserInfo(){

        return  UserInfo.builder().userId(userId).firstName(firstName).lastName(lastName).email(email).phoneNumber(phoneNumber).profilePic(profilePic).build();
    }
}