package com.healthpulse.backend.api.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProvidersRequest {

    private String name;
    private Integer stateCode;
    private Integer districtCode;
}
