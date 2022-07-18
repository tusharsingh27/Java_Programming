package com.healthpulse.backend.api.model.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateAddressRequest {

    private long userId;
    private String type;
    private String house;
    private String street;
    private String landmark;
    private String villageTownCity;
    private String district;
    private String pincode;
    private String state;
}
