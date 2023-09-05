package com.onehealth.pharmainventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class OneHealthPharmaInventoryApplication {

	// Logger for this class
	private static final Logger logger = LoggerFactory.getLogger(OneHealthPharmaInventoryApplication.class);

	public static void main(String[] args) {
		// Application startup log
		logger.info("Starting OneHealth-PharmaInventory Application...");
		SpringApplication.run(OneHealthPharmaInventoryApplication.class, args);
	}

	/**
	 * Handles a GET request to the root endpoint. Returns a welcome message.
	 *
	 * @return A welcome message.
	 */
	@GetMapping
	public String welcome() {
		// Log the incoming request
		logger.info("Received a GET request to the root endpoint");
		return "Welcome From OneHealth Team (OneHealth-PharmaInventory)!!!";
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
	return new RestTemplate();
	}

}
