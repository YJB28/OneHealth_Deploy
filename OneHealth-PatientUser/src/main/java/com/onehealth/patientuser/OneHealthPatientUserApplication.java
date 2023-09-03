package com.onehealth.patientuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class OneHealthPatientUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneHealthPatientUserApplication.class, args);
	}
	
	@GetMapping
	public String Welcome() {
		
		return "Welcome From OneHealth Team (OneHealth-PatientUser)!!!";
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
	return new RestTemplate();
	}

}
