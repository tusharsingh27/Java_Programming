package com.healthpulse.backend.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.healthpulse.backend.api.model.healthSystem.CreatePhrRequest;
import com.healthpulse.backend.api.model.authentication.GenerateOtpRequest;
import com.healthpulse.backend.api.model.healthSystem.AadhaarHealthIdRequest;
import com.healthpulse.backend.api.model.authentication.VerifyOtpRequest;
import com.healthpulse.backend.api.model.healthSystem.MobileHealthIdRequest;
import com.healthpulse.backend.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Auth API", description = "Authentication Authorisation API for Pulse Users")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration/generateOtp")
    @Operation(summary = "Generate OTP")
    public ResponseEntity<?> generateOtp(@RequestBody GenerateOtpRequest request, @RequestHeader("mode") String mode) throws URISyntaxException {
        return ResponseEntity.ok(authService.generateOtp(request, mode));
    }

    @PostMapping("/registration/verifyOtp")
    @Operation(summary = "Verify OTP")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest request, @RequestHeader("mode") String mode) throws URISyntaxException, JsonProcessingException {
        return ResponseEntity.ok(authService.verifyOtp(request, mode));
    }

    @PostMapping("/registration/resendOtp")
    @Operation(summary = "Resend OTP")
    public ResponseEntity<?> resendOtp(@RequestHeader("txnId") String txnId, @RequestHeader("mode") String mode) throws URISyntaxException {
        return ResponseEntity.ok(authService.resendOtp(txnId, mode));
    }

    @PostMapping("/registration/createHealthId")
    @Operation(summary = "Create Health Id via aadhaar")
    public ResponseEntity<?> createHealthId(@RequestBody AadhaarHealthIdRequest request) throws URISyntaxException {
        return ResponseEntity.ok(authService.createHealthIdViaAadhaar(request));
    }

    @PostMapping("/registration/createHidViaMobile")
    @Operation(summary = "Create Health Id via mobile")
    public ResponseEntity<?> createHealthId(@RequestBody MobileHealthIdRequest request) throws URISyntaxException {
        return ResponseEntity.ok(authService.createHealthIdViaMobile(request));
    }

    @PostMapping("/registration/confirm")
    @Operation(summary = "Confirm Health ID")
    public ResponseEntity<?> confirm(@RequestBody AadhaarHealthIdRequest request) throws URISyntaxException {
        return ResponseEntity.ok(authService.confirmHealthId(request,"aadhaar"));
    }

    @PostMapping("/registration/createPhrId")
    @Operation(summary = "")
    public ResponseEntity<?> confirm(@RequestBody CreatePhrRequest request) throws URISyntaxException {
        return ResponseEntity.ok(authService.linkPHRId(request,"aadhaar"));
    }

    @PostMapping("/login/generateOtp")
    @Operation(summary = "Generate OTP")
    public ResponseEntity<?> generateOtpForLogin(@RequestBody GenerateOtpRequest request, @RequestHeader("mode") String mode) throws URISyntaxException {
        return ResponseEntity.ok(authService.generateOtpForLogin(request, mode));
    }

    @PostMapping("/login/verifyOtp")
    @Operation(summary = "Verify OTP")
    public ResponseEntity<?> verifyOtpForLogin(@RequestBody VerifyOtpRequest request, @RequestHeader("mode") String mode) throws URISyntaxException {
        return ResponseEntity.ok(authService.verifyOtpForLogin(request, mode));
    }

}
