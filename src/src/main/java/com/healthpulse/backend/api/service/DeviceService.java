package com.healthpulse.backend.api.service;

import com.healthpulse.backend.api.persist.entity.DeviceInfoEntity;
import com.healthpulse.backend.api.persist.repository.DeviceInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceInfoRepo deviceInfoRepo;

    public DeviceInfoEntity getDeviceInfoById(long id) {
        return deviceInfoRepo.findById(id);

    }
    public DeviceInfoEntity submitDeviceInfo(DeviceInfoEntity deviceInfoEntity) {
        return deviceInfoRepo.save(deviceInfoEntity);
    }

    public DeviceInfoEntity getDeviceInfoByType(String type){
    return deviceInfoRepo.findByType(type);
    }

}
