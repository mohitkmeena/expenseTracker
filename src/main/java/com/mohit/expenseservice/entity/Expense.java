package com.mohit.expenseservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Expense {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "external_id")
    private String externalId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "merchant")
    private  String merchant;
    @Column(name = "amount")
    private String amount;
    @Column(name = "currency")
    private String currency;
    @Column(name = "created_at" , updatable=false)
    private Timestamp createdAt;

    @PrePersist
    public void updatetime(){
        createdAt=Timestamp.from(Instant.now());
    }
}
