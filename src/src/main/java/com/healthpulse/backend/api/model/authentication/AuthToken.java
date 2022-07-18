package com.healthpulse.backend.api.model.authentication;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.healthpulse.backend.api.service.AuthService.authToken;

@Setter
@Getter
public class AuthToken {

    private String accessToken;
    private int expiresIn;
    private int refreshExpiresIn;
    private String refreshToken;
    private String tokenType;
    private LocalDateTime timestamp;

    public boolean isExpired(){
        if(this.getTimestamp().isBefore(LocalDateTime.now().minusMinutes(5)))
            return true;
        else
            return false;
    }
}
