package com.healthpulse.backend.api.model.authentication;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {
    private String token;
    private String refreshToken;
    private Integer expiresIn;
    private Integer refreshExpiresIn;
}
