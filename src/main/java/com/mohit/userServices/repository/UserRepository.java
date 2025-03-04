package com.mohit.userServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.userServices.entities.UserInfo;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,String> {


    Optional<UserInfo> findByUserId(String userId);
    UserInfo save(UserInfo userInfo);
}
