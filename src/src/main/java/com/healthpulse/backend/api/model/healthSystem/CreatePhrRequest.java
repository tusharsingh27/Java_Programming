package com.healthpulse.backend.api.model.healthSystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePhrRequest {

    private String phrAddress;
    private String txnId;

}
