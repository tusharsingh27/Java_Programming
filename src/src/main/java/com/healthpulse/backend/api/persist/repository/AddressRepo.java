package com.healthpulse.backend.api.persist.repository;

import com.healthpulse.backend.api.persist.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity, Long> {

    List<AddressEntity> findAllByUserId(long id);
}
