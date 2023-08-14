package com.oneHealth.Video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OneHealthVideoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneHealthVideoApplication.class, args);
	}
	
	@GetMapping
	public String Welcome() {
		
		return "Welcome To WEbRTC Service";
	}

}
