package com.mohit.expensetracker.auth.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name = "userid")
    private String userid;
    @Column(name="username")
    private String username;
    @Column(name="password")
    @Size(min = 8,message = "password should be of minimum  8 length")
    private String password;
    @Column(name = "email")
    @Email(message = "please enter a valid email")
    private String email;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="user_roles",
        joinColumns=@JoinColumn(name="userid"),
        inverseJoinColumns=@JoinColumn(name="role_id")
    )
    private Set<UserRole>roles=new HashSet<>();



}
