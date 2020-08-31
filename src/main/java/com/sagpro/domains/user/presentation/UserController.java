package com.sagpro.domains.user.presentation;

import com.sagpro.domains.user.application.UserService;
import com.sagpro.domains.user.application.dto.UserRequest;
import com.sagpro.domains.user.application.dto.UserResponse;
import com.sagpro.domains.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/add")
    public UserResponse signIn (@RequestBody UserRequest userRequest){
        return userService.userAdd(userRequest);
    }

    @DeleteMapping("/user/del")
    public UserResponse delUser (@RequestBody UserRequest userRequest){

        return userService.userDel(userRequest);
    }

    @PutMapping("/user/update")
    public UserResponse updateUser(@RequestBody UserRequest userRequest){

        return userService.updateUser(userRequest);
    }

    @GetMapping("/user/all")
    public List<UserEntity> allUser(){
        return userService.userAll();
    }

    @PostMapping("/user/login")
    public UserResponse userLogin(@RequestBody UserRequest userRequest){
        System.out.println(userRequest.getUserId());
        return userService.userLogin(userRequest);

    }


}
