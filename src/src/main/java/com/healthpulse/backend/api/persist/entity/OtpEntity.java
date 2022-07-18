package com.healthpulse.backend.api.persist.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table( name = "otp_info")
public class OtpEntity extends BaseEntity {

    @Id
    private String txnId = UUID.randomUUID().toString();
    private String sendTo;
    private Integer otp;
    private Boolean isValid = true;
}
