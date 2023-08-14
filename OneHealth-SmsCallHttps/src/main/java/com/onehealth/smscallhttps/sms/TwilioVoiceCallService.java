package com.onehealth.smscallhttps.sms;

//package com.example.videocallbackend.service;

import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;
//import com.twilio.type.IpMessagingGrant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioVoiceCallService {

    @Value("ACd51a4c20910ca60fadd6b656475b92d0")
    private String apiKeySid;

    @Value("7e2da9f5f7fc7cbfa094863919079071")
    private String apiKeySecret;

    @Value("7e2da9f5f7fc7cbfa094863919079071")
    private String accountSid;

    public String generateAccessToken(String identity) {
        AccessToken token = new AccessToken.Builder(
                accountSid,
                apiKeySid,
                apiKeySecret
        ).identity(identity).grant(new VideoGrant()).build();

        return token.toJwt();
    }
}