package com.onehealth.pharmainventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@SpringBootApplication(exclude = {OneHealthPharmaInventoryApplication.class })
@RestController
public class OneHealthPharmaInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneHealthPharmaInventoryApplication.class, args);
	}
	
	@GetMapping
	public String welcome() {
		
		return "Welcome From OneHealth Team (OneHealth-PharmaInventory)!!!";
	}

}
