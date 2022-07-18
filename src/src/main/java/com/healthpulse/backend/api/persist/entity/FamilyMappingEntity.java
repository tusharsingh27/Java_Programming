package com.healthpulse.backend.api.persist.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table( name = "family_mapping")
@NoArgsConstructor
public class FamilyMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private long userId;
    private long memberId;
    private String relation;


    public FamilyMappingEntity(long userId, long memberId, String relation) {
        this.userId = userId;
        this.memberId = memberId;
        this.relation = relation;
    }
}
