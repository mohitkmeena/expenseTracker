package com.mohit.expensetracker.auth.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class RefreshToken {
    @Id
    @Column(name="refresht_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    @Column(name="token")
    @NotBlank(message = "please enter refresh token value")
    private String token;
    @Column(name="exptime",nullable=false)
    private Instant expirationtime;
    @OneToOne
    @JoinColumn(name="id",referencedColumnName="userid")
    private User user;
}
