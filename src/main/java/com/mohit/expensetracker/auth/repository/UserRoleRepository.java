package com.mohit.expensetracker.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohit.expensetracker.auth.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    
}
