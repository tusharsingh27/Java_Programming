package com.healthpulse.backend.api.model.authentication;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenerateOtpRequest {

    private String value;
    private String txnId;
}
