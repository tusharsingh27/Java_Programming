package com.healthpulse.backend.api.model.user;

import com.healthpulse.backend.api.persist.entity.AddressEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserInfoResponse {

    private long id;

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
    private String qrCode;
    private List<AddressEntity> address;
    private List<FamilyProfile> familyMembers;
}
