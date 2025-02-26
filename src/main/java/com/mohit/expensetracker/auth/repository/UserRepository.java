package com.mohit.expensetracker.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mohit.expensetracker.auth.entity.User;

public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

   Optional< User> findByEmail(String email);
   @Modifying
   @Query("UPDATE User u SET u.password = :password WHERE u = :user")
   void updatePassword(@Param("user") User user, @Param("password") String password);

    
}
