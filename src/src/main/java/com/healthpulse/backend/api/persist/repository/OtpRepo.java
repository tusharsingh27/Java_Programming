package com.healthpulse.backend.api.persist.repository;

import com.healthpulse.backend.api.persist.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepo extends JpaRepository<OtpEntity, String> {


}
