package com.sagpro.domains.user.application;


import com.sagpro.domains.user.application.dto.UserRequest;
import com.sagpro.domains.user.application.dto.UserResponse;
import com.sagpro.domains.user.domain.UserEntity;
import com.sagpro.domains.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원등록
    @Transactional
    public UserResponse userAdd(UserRequest userRequest){
        String userId= userRequest.getUserId();
        //아이디 조회 후 중복된 값이 아닐 경우에만 추가
        //orElseGet 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 람다 표현식의 결괏값을 반환함.
        UserEntity useradd= userRepository.findByUserId(userId)
                .orElseGet(()->UserEntity
                        .builder()
                        .userId(userRequest.getUserId())
                        .userPw(userRequest.getUserPw())
                        .snsId(userRequest.getSnsId())
                        .build());
        userRepository.save(useradd);
        return UserResponse.builder().userId(useradd.getUserId()).build();
    }

    //회원 삭제
    //orElseThrow - 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 예외를 발생시킴.
    public UserResponse userDel(UserRequest userRequest){
        String userId= userRequest.getUserId();
        UserEntity user=userRepository.findByUserId(userId)
                .orElseThrow(()->new IllegalArgumentException("유저가 없다"));
        userRepository.delete(user);
        return UserResponse.builder().userId(user.getUserId()).build();

    }

    @Transactional
    public UserResponse updateUser(UserRequest userRequest){
        String userId=userRequest.getUserId();
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 요청입니다."));
        user.update(userRequest.getSnsId());
        return UserResponse.builder().userId(user.getUserId()).build();
    }

    public List<UserEntity> userAll(){
        return userRepository.findAll();
    }

    public UserResponse userLogin(UserRequest userRequest){
        System.out.println(userRequest.getUserId());
        UserEntity user = userRepository.loginUser(userRequest.getUserId(),userRequest.getUserPw())
                .orElseThrow(()-> new IllegalArgumentException("로그인 실패"));
        System.out.println("로그인 성공");
        return UserResponse.builder().userId(userRequest.getUserId()).build();

    }




}
