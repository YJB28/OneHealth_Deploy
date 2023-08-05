package com.onehealth.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//@RefreshScope
//@EnableEurekaClient //no need to from Boot version 3
public class OneHealthApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneHealthApiGatewayApplication.class, args);
	}
	
	@GetMapping
	public String Welcome() {
		
		return "Welcome From OneHealth Team (OneHealth-APIGateWay)!!!";
	}

}
