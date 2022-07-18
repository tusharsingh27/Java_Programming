package com.healthpulse.backend.api.model.authentication;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenerateOtpForAadhaarRequest {

    private String aadhaar;

}
