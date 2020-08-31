package com.sagpro.domains.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUserId(String userId);

    //UserEntity 클래스를 직접명시
    //@Param을 이용하여 정확히 해당하는 값 바인딩
    @Query("SELECT m FROM UserEntity AS m WHERE m.userId = :userId AND m.userPw = :userPw")
    Optional<UserEntity> loginUser(@Param("userId") String userId, @Param("userPw") String userPw);


}
