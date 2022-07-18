package com.healthpulse.backend.api.controller;

import com.healthpulse.backend.api.persist.entity.DeviceInfoEntity;
import com.healthpulse.backend.api.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_VALUE)
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("/device")
    @Operation(summary = "Add Device Information")
    public ResponseEntity<DeviceInfoEntity> submitDeviceInfo(@RequestBody DeviceInfoEntity deviceInfoEntity) {
        DeviceInfoEntity deviceInfo = deviceService.submitDeviceInfo(deviceInfoEntity);
        return new ResponseEntity<>(deviceInfo, HttpStatus.CREATED);
    }

    @GetMapping("/device/Id/{id}")
    @Operation(summary = "Get details of specific Device id")
    public ResponseEntity<DeviceInfoEntity> getDeviceInfoById(@PathVariable("id") Long id) {
        DeviceInfoEntity deviceInfo = deviceService.getDeviceInfoById(id);
        return new ResponseEntity<>(deviceInfo, HttpStatus.OK);
    }

    @GetMapping("/deviceInfo/Type/{type}")
    @Operation(summary = "Get details of specific Device Type")
    public ResponseEntity<DeviceInfoEntity> getDeviceInfoByType(@PathVariable("type") String type) {
        DeviceInfoEntity deviceInfo= deviceService.getDeviceInfoByType(type);
        return new ResponseEntity<>(deviceInfo, HttpStatus.OK);

    }
}
