package com.jicjo.apis.service.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jicjo.apis.dto.core.SmsRequestDto;
import com.jicjo.apis.dto.icp.PolicyRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ApiCollingService {
    String SendSms (SmsRequestDto smsRequestDto) throws JsonProcessingException;
    String createIcpPolicy(PolicyRequestDto policyRequestDto) throws JsonProcessingException;
}
