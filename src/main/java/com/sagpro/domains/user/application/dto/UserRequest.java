package com.sagpro.domains.user.application.dto;

import com.sagpro.domains.user.domain.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {
    private String userId;
    private String userPw;
    private String snsId;

    @Builder
    public UserRequest(String userId,String userPw,String snsId){
        this.userId=userId;
        this.userPw=userPw;
        this.snsId=snsId;
    }
}
