package com.healthpulse.backend.api.persist.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table( name = "address_detail")
public class AddressEntity extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private String type;
    private String house;
    private String street;
    private String landmark;
    private String villageTownCity;
    private String district;
    private String pincode;
    private String state;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_info_id", nullable=false)
    private AddressEntity address;
}
