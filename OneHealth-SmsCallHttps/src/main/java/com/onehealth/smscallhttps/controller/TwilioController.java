package com.onehealth.smscallhttps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onehealth.smscallhttps.sms.TwilioSmsService;
import com.onehealth.smscallhttps.sms.TwilioVoiceCallService;

@RestController
public class TwilioController {

	private final TwilioSmsService smsService;

	@Autowired
	public TwilioController(TwilioSmsService smsService) {
		this.smsService = smsService;
	}

	@PostMapping("/send-sms")
	public String sendSms(@RequestBody SmsRequest smsRequest) {
	    smsService.sendSms(smsRequest.getTo(), smsRequest.getMessage());
	    return "SMS sent successfully";
	}
}

class SmsRequest {
	private String to;
	private String message;

	public SmsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SmsRequest [to=" + to + ", message=" + message + "]";
	}

	public SmsRequest(String to, String message) {
		super();
		this.to = to;
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// Getters and setters
}
