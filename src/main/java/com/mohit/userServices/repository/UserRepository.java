package com.mohit.userServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.userServices.entities.UserInfo;
@Repository
public interface UserRepository extends JpaRepository<UserInfo,String> {

    
}
