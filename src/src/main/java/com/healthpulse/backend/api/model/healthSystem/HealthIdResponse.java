package com.healthpulse.backend.api.model.healthSystem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthpulse.backend.api.model.authentication.JwtResponse;
import com.healthpulse.backend.api.model.user.FamilyProfile;
import com.healthpulse.backend.api.persist.entity.AddressEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthIdResponse {

    private String name;
    private String birthdate;
    private String aadhaar;
    private String healthId;
    private String healthIdNumber;
    private String gender;
    private String careOf;
    private String photo;
    private String email;
    private String phone;
    private String villageTownCity;
    private String district;
    private String state;
    private String pinCode;
    @JsonProperty("jwtResponse")
    private JwtResponse jwt;
    private String qrCode;
}
