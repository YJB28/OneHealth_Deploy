package com.onehealth.smscallhttps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;


@SpringBootApplication
@RestController
public class OneHealthSmsCallHttpsApplication {
	
	private final static String ACCOUNT_SID = "ACd51a4c20910ca60fadd6b656475b92d0";
	private final static String AUTH_ID = "7e2da9f5f7fc7cbfa094863919079071";
	
	static {
	Twilio.init(ACCOUNT_SID, AUTH_ID);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OneHealthSmsCallHttpsApplication.class, args);
	}
	
	@GetMapping
	public String Welcome() {
		
		return "Welcome From OneHealth Team (OneHealth-SMSVoiceCallwithHTTPS)!!!";
	}
	
	


}
