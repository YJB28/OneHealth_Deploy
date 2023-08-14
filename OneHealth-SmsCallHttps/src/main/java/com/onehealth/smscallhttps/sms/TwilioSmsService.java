package com.onehealth.smscallhttps.sms;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService {

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public void sendSms(String to, String message) {
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(twilioPhoneNumber),
                message
        ).create();
    }
}
