package com.mohit.userServices.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserInfo {

    @Id
    @NonNull
    private String userId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String phoneNumber;

    private String profilePic;
    
}