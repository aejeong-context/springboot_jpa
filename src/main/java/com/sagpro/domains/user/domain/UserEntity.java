package com.sagpro.domains.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String userPw;
    private String snsId;

    @Builder
    public UserEntity(String userId,String userPw,String snsId){
        this.id=id;
        this.userId=userId;
        this.userPw=userPw;
        this.snsId=snsId;
    }

    public void update(String snsId){
        this.snsId=snsId;
    }

}
