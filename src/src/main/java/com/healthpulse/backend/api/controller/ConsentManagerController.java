package com.healthpulse.backend.api.controller;

import com.healthpulse.backend.api.model.ProvidersRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.healthpulse.backend.api.service.AuthService;
import java.net.URISyntaxException;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Consent Manager", description = "Consent managers API for Pulse Users")
public class ConsentManagerController {

    private final AuthService authService;

    @GetMapping("/providers")
    @Operation(summary = "List providers by given name")
    public ResponseEntity<?> getProviders(@RequestBody ProvidersRequest request) throws URISyntaxException {
        return ResponseEntity.ok(authService.getProvidersList(request));
    }

}
