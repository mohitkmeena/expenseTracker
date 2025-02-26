package com.mohit.expensetracker.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mohit.expensetracker.auth.entity.ForgetPassword;
import com.mohit.expensetracker.auth.entity.User;
@Repository
public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword,Integer> {
    @Query("SELECT fp FROM ForgetPassword fp WHERE fp.otp = :otp AND fp.user = :user")
Optional<ForgetPassword> findByOtpAndUser(@Param("otp") Integer otp, @Param("user") User user);

    ForgetPassword findByUser(User user);
}
