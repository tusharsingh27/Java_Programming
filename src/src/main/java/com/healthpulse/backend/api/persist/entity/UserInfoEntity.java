package com.healthpulse.backend.api.persist.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table( name = "user_info")
public class UserInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_info_seq")
    private long id;

    private String name;
    private LocalDate birthdate;
    private String aadhaar = "";
    private String healthId = "";
    private String healthIdNumber = "";
    private String gender = "";
    private String careOf = "";
    @Column(columnDefinition="LONGTEXT")
    private String photo = "";
    private String email = "";
    private String phone = "";
    @Column(columnDefinition="LONGTEXT")
    private String qrCode = "";

    @OneToMany(mappedBy="address", fetch = FetchType.EAGER)
    private List<AddressEntity> address;

}