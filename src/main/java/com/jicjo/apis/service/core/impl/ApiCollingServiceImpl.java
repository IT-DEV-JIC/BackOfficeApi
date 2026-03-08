package com.jicjo.apis.service.core.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jicjo.apis.dto.core.SmsRequestDto;
import com.jicjo.apis.dto.icp.PolicyRequestDto;
import com.jicjo.apis.service.core.ApiCollingService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serial;
import java.io.Serializable;

@Service
public class ApiCollingServiceImpl implements ApiCollingService, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String url   = "https://zsms.jo.zain.com/core/corpsms/sendNow";

    @Override
    public String SendSms(SmsRequestDto smsRequestDto) throws JsonProcessingException {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjb3Jwc21zIiwic3ViIjoiOTYyNzkxMTQ3ODQ1IiwiaWF0IjoxNzU2ODg0MzgwfQ.zl8abUrCNVWW9KC9y7Rs7hnYDqzeTXzBZPFeCUnC-o0kYrsGRlYtUGzpeTj8QtOuGsu8CEElPWBu6c5p7mea2w";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonBody = objectMapper.writeValueAsString(smsRequestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("integration_token", token);

        // Create the HttpEntity with the request body and headers
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        // Send POST request using RestTemplate
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
        );

        return response.getBody();
    }

    @Override
    public String createIcpPolicy(PolicyRequestDto policyRequestDto) throws JsonProcessingException {
        this.url = "https://api.slashdata.ae/policy/createpolicy";

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonBody = objectMapper.writeValueAsString(policyRequestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the HttpEntity with the request body and headers
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        // Send POST request using RestTemplate
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
        );

        return response.getBody();
    }
}
