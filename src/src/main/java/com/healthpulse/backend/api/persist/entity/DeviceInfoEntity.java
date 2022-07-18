package com.healthpulse.backend.api.persist.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table( name = "device_info")
public class DeviceInfoEntity  {

    @Id
    private long id;
    private String type;
    private String status;
}

