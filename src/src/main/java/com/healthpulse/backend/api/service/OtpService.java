package com.healthpulse.backend.api.service;

import com.healthpulse.backend.api.exeption.DataNotFoundException;
import com.healthpulse.backend.api.model.authentication.GenerateOtpResponse;
import com.healthpulse.backend.api.model.authentication.VerifyOtpRequest;
import com.healthpulse.backend.api.model.authentication.VerifyOtpResponse;
import com.healthpulse.backend.api.persist.entity.OtpEntity;
import com.healthpulse.backend.api.persist.repository.OtpRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class OtpService {

    private final OtpRepo repository;
    //private final NotificationService notification;

    public GenerateOtpResponse generateOTP(String key){
        OtpEntity otp = new OtpEntity();
        otp.setOtp(getRandomOtp());
        otp.setSendTo(key);
        otp = repository.save(otp);
        log.info("Otp Generated for key:" + key);
        //AwsSNSService.pubTextSMS("Pulse Otp:" + otp.getOtp().toString(),key);
        TwilioSMSService.pubTextSms("Your Pulse System One Time Password [OTP] is: " + otp.getOtp().toString(),key);
        return new GenerateOtpResponse(otp.getTxnId());
    }

    public VerifyOtpResponse verifyOTP(VerifyOtpRequest data){
        OtpEntity otp = repository.findById(data.getTxnId()).get();
              //  .orElseThrow(new DataNotFoundException("No OTP Found for this txnId"));
        // LocalDateTime.now().isAfter(otp.getCreatedDate().plusMinutes(10))
        if(!(otp.getIsValid() && otp.getOtp().toString().equalsIgnoreCase(data.getOtp()))){
            throw new DataNotFoundException("Invalid Otp");
        }
        return new VerifyOtpResponse(data.getTxnId());
    }

    private int getRandomOtp(){
        Random random = new Random();
        return random.nextInt(900000)+100000;
    }
}
