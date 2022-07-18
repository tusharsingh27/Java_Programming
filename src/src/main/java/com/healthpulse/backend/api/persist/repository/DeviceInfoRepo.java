package com.healthpulse.backend.api.persist.repository;

import com.healthpulse.backend.api.persist.entity.DeviceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeviceInfoRepo extends JpaRepository<DeviceInfoEntity, Long> {

     DeviceInfoEntity findById(long id);

    DeviceInfoEntity findByType(String type);

    @Query("SELECT a FROM DeviceInfoEntity a WHERE DeviceInfoEntity_type like concat('%',:type,'%')")
    public List<DeviceInfoEntity> DeviceInfoEntity(@Param("type") String type);

}
