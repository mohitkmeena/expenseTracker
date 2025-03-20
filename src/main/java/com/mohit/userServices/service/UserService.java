package com.mohit.userServices.service;

import com.mohit.userServices.entities.UserInfo;
import com.mohit.userServices.entities.UserInfoDto;
import com.mohit.userServices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Service
public class UserService {
    /*
    *
    * */
    @Autowired
    private UserRepository userRepository;

    public UserInfoDto createOrUpdateUser(UserInfoDto userInfoDto){
        Function<UserInfo,UserInfo> updatingUser=user->{
            //TODO :Implement the update
                user.setUserId(userInfoDto.getUserId());
                user.setEmail(userInfoDto.getEmail());
                user.setFirstName(userInfoDto.getFirstName());
                user.setLastName(userInfoDto.getLastName());
                user.setPhoneNumber(userInfoDto.getPhoneNumber());
                user.setProfilePic(userInfoDto.getProfilePic());
                 return userRepository.save(user);
         };
        Supplier<UserInfo> createUser=()->{
            return userRepository.save(userInfoDto.transferToUserInfo());
        };
//        UnaryOperator<UserInfo>update=userInfo -> {
//            /*
//            same as function as return same what it take
//            */
//        };
        UserInfo userInfo=userRepository.findByUserId(userInfoDto.getUserId()).map(updatingUser).orElseGet(createUser);

        return UserInfoDto.builder()
                        .userId(userInfo.getUserId())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .email(userInfo.getEmail())
                .phoneNumber(userInfo.getPhoneNumber())
                .profilePic(userInfo.getProfilePic())
                .build();
    }

    public UserInfoDto getUser(UserInfoDto userInfoDto)  throws Exception{
        Optional<UserInfo> userInfoOptional=userRepository.findByUserId(userInfoDto.getUserId());
if(userInfoOptional.isEmpty()){
    throw new Exception("user not found");
}
UserInfo userInfo=userInfoOptional.get();
        return UserInfoDto.builder()
                .userId(userInfo.getUserId())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .email(userInfo.getEmail())
                .phoneNumber(userInfo.getPhoneNumber())
                .profilePic(userInfo.getProfilePic())
                .build();
    }
}
