package com.healthpulse.backend.api.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioSMSService {

    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "ACfd975ec4f1fc9a768640ded66e3bd3cf";
    public static final String AUTH_TOKEN  = "5cb7c9bfe6a5265c71b175377c6c207e";
    public static final String TWILIO_NUMBER = "+19706709013";
    public static void  pubTextSms(String body, String phoneNumber){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber("+91"+phoneNumber),
                        new PhoneNumber(TWILIO_NUMBER), body)
                .create();
    }
}
