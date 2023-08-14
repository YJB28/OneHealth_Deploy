package com.oneHealth.pharmacyInventoryManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PharmacyInventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyInventoryManagementApplication.class, args);
	}
	
	@GetMapping
	public String Welcome() {
		
		return "Welcome From Infobell Backend Team (OneHealth-InventoryManagement)!!";
	}

}
