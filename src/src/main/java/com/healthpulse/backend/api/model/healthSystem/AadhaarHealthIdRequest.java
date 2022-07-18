package com.healthpulse.backend.api.model.healthSystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AadhaarHealthIdRequest {

    private Boolean consent;
    private String consentVersion;
    private String txnId;

}
