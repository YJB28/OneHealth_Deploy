package com.onehealth.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@CrossOrigin("*")
@RestController

//@RefreshScope
//@EnableEurekaClient //no need to from Boot version 3
public class OneHealthApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneHealthApiGatewayApplication.class, args);
	}
	
	@GetMapping
	public String Welcome() {
		
		return "Welcome From Infobell Backend Team2 (OneHealth-APIGateWay)!!!";
	}
	
	@PostMapping
	public String Welcome1(@RequestBody String name) {
		
		return "Your name is....."+name;
	}

}
