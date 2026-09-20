package com.jicjo.apis.service.ai.impl;

import com.jicjo.apis.dto.ai.ComplaintAiAnalysisRequest;
import com.jicjo.apis.dto.ai.ComplaintAiAnalysisResponse;
import com.jicjo.apis.service.ai.ComplaintAiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ComplaintAiServiceImpl implements ComplaintAiService {

    private final ChatClient chatClient;

    public ComplaintAiServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public ComplaintAiAnalysisResponse analyzeComplaint(ComplaintAiAnalysisRequest request) {
        String systemPrompt = """
                You are an AI assistant for Jordan Insurance Company complaint management system.

                Analyze the customer complaint and return:
                - category
                - sentiment
                - priority
                - short summary
                - suggested official reply

                Rules:
                1. Return the response in the same language of the complaint.
                2. Do not make final legal decisions.
                3. Do not promise compensation.
                4. Suggested reply must be formal and suitable for insurance company communication.
                5. Priority should be one of: LOW, MEDIUM, HIGH, URGENT.
                6. Sentiment should be one of: SATISFIED, NEUTRAL, ANGRY, VERY_ANGRY.
                7. Category should be one of:
                   CLAIM_DELAY,
                   BAD_SERVICE,
                   POLICY_ISSUE,
                   PAYMENT_ISSUE,
                   TECHNICAL_ISSUE,
                   SUGGESTION,
                   OTHER.
                """;

        String userPrompt = """
                Complaint ID: %s
                Complaint Title: %s
                Complaint Body: %s
                Complaint Language: %s
                Insurance Type: %s
                Complaint Type: %s
                """.formatted(
                request.cstCmpId(),
                request.title(),
                request.body(),
                request.language(),
                request.insuranceType(),
                request.complaintType()
        );

        return chatClient.prompt()
                .system(systemPrompt)
                .user(userPrompt)
                .call()
                .entity(ComplaintAiAnalysisResponse.class);
    }
}
