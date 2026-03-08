package com.jicjo.apis.service.core.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jicjo.apis.dto.core.SmsRequestDto;
import com.jicjo.apis.service.core.ApiCollingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, String> otpStore = new ConcurrentHashMap<>();
    private final ApiCollingService apiCollingService;

    @Autowired
    public OtpService(ApiCollingService apiCollingService) {
        this.apiCollingService = apiCollingService;
    }

    public void generateAndSendOtp(String username, String phoneNumber) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStore.put(username, otp);

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumber);

        if (username.toLowerCase().equals("system")){
            phoneNumbers.add("0796886621");
            phoneNumbers.add("0788781953");
            phoneNumbers.add("0788254320");
            phoneNumbers.add("0791187670");
        }

        SmsRequestDto smsDto = new SmsRequestDto();
        smsDto.setService_type("bulk_sms");
        smsDto.setRecipient_numbers_type("single_numbers");
        smsDto.setPhone_numbers(phoneNumbers);
        smsDto.setContent("Your OTP code is: " + otp);
        smsDto.setSender_id("JIC");

        try {
            apiCollingService.SendSms(smsDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to send OTP SMS", e);
        }
    }

    public boolean verifyOtp(String username, String otp) {
        return otp.equals(otpStore.get(username));
    }

    public void clearOtp(String username) {
        otpStore.remove(username);
    }
}

