package com.healthpulse.backend.api.model.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class AddUserRequest {

    private String name;
    private LocalDate birthdate;
    private String gender;
    private String phone;

}
