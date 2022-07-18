package com.healthpulse.backend.api.controller;

import com.healthpulse.backend.api.model.authentication.RegisterUserRequest;
import com.healthpulse.backend.api.model.user.UpdateAddressRequest;
import com.healthpulse.backend.api.model.user.UserInfoRequest;
import com.healthpulse.backend.api.model.user.UserInfoResponse;
import com.healthpulse.backend.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_VALUE)
@Tag(name = "User API", description = "User API for Pulse Users")
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    @Operation(summary = "Get details of all Users")
    public ResponseEntity<?> listAllUserInfo() {
        return ResponseEntity.ok(userService.getUserInfo());
    }

//    @GetMapping("/user/{id}")
//    @Operation(summary = "Get details of User By Id")
//    public ResponseEntity<?> getUserInfoById(@PathVariable("id") long id) {
//        return ResponseEntity.ok(userService.getUserInfoById(id));
//    }

    @GetMapping("/user/{mobile}")
    @Operation(summary = "Get details of User By mobile")
    public ResponseEntity<?> getUserInfoByMobile(@PathVariable("mobile") String mobile) {
        return ResponseEntity.ok(userService.getUserInfoByMobile(mobile));
    }

    @GetMapping("/user/{id}/profile")
    @Operation(summary = "Get details of User By Id")
    public ResponseEntity<?> getUserInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserInfoById(id));
    }

    @PostMapping("/user")
    @Operation(summary = "Add User first time")
    public ResponseEntity<UserInfoResponse> addUserInfo(@RequestBody RegisterUserRequest request) {
        return ResponseEntity.ok(userService.addUserInfo(request));
    }

    @PostMapping("/user/{id}/family")
    @Operation(summary = "Add Family member")
    public ResponseEntity<UserInfoResponse> addFamilyMember(@PathVariable("id") long id,@RequestBody UserInfoRequest request) {
        return ResponseEntity.ok(userService.addFamilyMember(id,  request));
    }

    @DeleteMapping("/user/{id}/family/(memberId)")
    @Operation(summary = "Remove Family member")
    public ResponseEntity<UserInfoResponse> removeFamilyMember(@PathVariable("id") long userId,@PathVariable("memberId") long memberId) {
        return ResponseEntity.ok(userService.removeFamilyMember(userId, memberId));
    }

    @PutMapping("/user/{id}")
    @Operation(summary = "Update User Information")
    public ResponseEntity<UserInfoResponse> updateUserInfo(@PathVariable("id") long id,@RequestBody UserInfoRequest request) {
        return ResponseEntity.ok(userService.updateUserInfo(id,request));
    }

    @PutMapping("/user/{id}/address")
    @Operation(summary = "Update User Address")
    public ResponseEntity<UserInfoResponse> updateUserAddress(@PathVariable("id") long id,@RequestBody UpdateAddressRequest request) {
        return ResponseEntity.ok(userService.updateAddress(id,request));
    }


}
