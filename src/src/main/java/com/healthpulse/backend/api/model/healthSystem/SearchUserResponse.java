package com.healthpulse.backend.api.model.healthSystem;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.List;

@Setter
@Getter
public class SearchUserResponse {

    private String name;
    private String gender;
    private Integer yearOfBirth;
    private String healthId;
    private String healthIdNumber;
    private String status;
    private List<String> authMethods;

}
