package com.healthpulse.backend.api.model.authentication;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
public class RegisterUserRequest {

    private String name;
    private LocalDate birthdate;
    private String gender;
    private String phone;

}
