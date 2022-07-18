package com.healthpulse.backend.api.model.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserInfoRequest {

    private String name;
    private LocalDate birthdate;
    private String aadhaar;
    private String healthId;
    private String healthIdNumber;
    private String gender;
    private String careOf;
    private String photo;
    private String email;
    private String phone;
    private String relation;
    private String qrCode;

}
