package com.healthpulse.backend.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
	@Info(title = "Pulse Backend API",
		version = "1.0",
		description = "Springboot Backend API for Pulse App"))
public class PulseBackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PulseBackendApiApplication.class, args);
	}

}
