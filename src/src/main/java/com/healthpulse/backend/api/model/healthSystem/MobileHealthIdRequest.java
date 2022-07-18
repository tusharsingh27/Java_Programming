package com.healthpulse.backend.api.model.healthSystem;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class MobileHealthIdRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthdate;
    private String gender;
    private String phone;
    private String stateCode;
    private String districtCode;
    private String email;
    private String txnId;
    private String healthId;
    private String password;

}
