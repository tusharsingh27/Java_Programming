package com.healthpulse.backend.api.persist.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table( name = "health_info")
public class HealthInfoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private String healthId;
    private String healthIdNumber;
}
