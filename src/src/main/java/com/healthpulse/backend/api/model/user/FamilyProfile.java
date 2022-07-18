package com.healthpulse.backend.api.model.user;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class FamilyProfile {
    private long id;
    private String name;
    private String healthId;
    private String healthIdNumber;
    private String gender;
    private String photo;
    private String relation;
}
