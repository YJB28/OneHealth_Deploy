package com.onehealth.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
	
	@GetMapping
	public String Welcome1() {
		
		return "Welcome From OneHealth Team (OneHealth-ConfigServer)!!!";
	}
	
	@GetMapping("/welcome1")
	public String Welcome12() {
		
		return "Welcome From OneHealth Team (OneHealth-ConfigServer)!!!";
	}

}
