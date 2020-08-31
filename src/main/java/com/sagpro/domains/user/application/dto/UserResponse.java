package com.sagpro.domains.user.application.dto;

import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class UserResponse {
    private String userId;

}
