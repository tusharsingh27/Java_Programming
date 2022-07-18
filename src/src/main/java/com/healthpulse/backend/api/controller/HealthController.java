package com.healthpulse.backend.api.controller;

import com.healthpulse.backend.api.model.authentication.RegisterUserRequest;
import com.healthpulse.backend.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Health API", description = "Health System APIs for Pulse Users")
public class HealthController {

    private final AuthService authService;

    @GetMapping("/searchByHId")
    @Operation(summary = "Search By Health ID")
    public ResponseEntity<?> searchByHid(@RequestHeader("healthId") String id) throws URISyntaxException {
        return ResponseEntity.ok(authService.searchByHid(id));
    }

    @PostMapping("/searchByMobile")
    @Operation(summary = "Search By Basic Details like Name, Mobile, DOB etc.")
    public ResponseEntity<?> searchByMobile(@RequestBody RegisterUserRequest request) throws Exception {
        return ResponseEntity.ok(authService.searchByMobile(request));
    }

    @PostMapping("/userHidLookup")
    @Operation(summary = "Search By Basic Details like Name, Mobile, DOB etc.")
    public ResponseEntity<?> searchHidByUserInfo(@RequestHeader("userId") Long id,
                                            @RequestBody RegisterUserRequest request) throws Exception {
        return ResponseEntity.ok(authService.userHidLookup(request,id));
    }

    @GetMapping("/existByHid")
    @Operation(summary = "Exist By Health ID")
    public ResponseEntity<?> existByHid(@RequestHeader("healthId") String id) throws URISyntaxException {
        return ResponseEntity.ok(authService.existByHid(id));
    }

    @GetMapping("/qrCode")
    @Operation(summary = "Get QR Code for User")
    public ResponseEntity<?> getQRCode(@RequestHeader("token") String token) throws URISyntaxException {
        //return ResponseEntity.ok(authService.getQRCode(token));
        return ResponseEntity.ok()
                //.contentType(MediaType.IMAGE_PNG_VALUE)
                //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(authService.getQRCode(token));
    }

    @GetMapping("/data/state")
    @Operation(summary = "Get list of States and their code")
    public ResponseEntity<?> getStateList() throws URISyntaxException {
        return ResponseEntity.ok(authService.getStates());
    }

    @GetMapping("/data/state/{id}")
    @Operation(summary = "Get list of districts for state")
    public ResponseEntity<?> getDistrictList(@PathVariable("id") String id) throws URISyntaxException {
        return ResponseEntity.ok(authService.getDistricts(id));
    }
}
