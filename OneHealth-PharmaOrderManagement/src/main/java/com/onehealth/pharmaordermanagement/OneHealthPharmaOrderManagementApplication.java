package com.onehealth.pharmaordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OneHealthPharmaOrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneHealthPharmaOrderManagementApplication.class, args);
	}
	
	@GetMapping
	public String welcome() {
		
		return "Welcome From OneHealth Team (OneHealth-PharmaOrderManagement)!!!";
	}

}
