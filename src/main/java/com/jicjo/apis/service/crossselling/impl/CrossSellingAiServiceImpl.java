package com.jicjo.apis.service.crossselling.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.jicjo.apis.dto.core.AiSetupDto;
import com.jicjo.apis.dto.crossselling.CrossSellCandidateDto;
import com.jicjo.apis.dto.crossselling.CrossSellingAiRequestDto;
import com.jicjo.apis.dto.crossselling.CrossSellingAiResponseDto;
import com.jicjo.apis.dto.crossselling.CrossSellingOpportunityDto;
import com.jicjo.apis.service.core.AiSetupService;
import com.jicjo.apis.service.core.OllamaService;
import com.jicjo.apis.service.crossselling.CrossSellingAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrossSellingAiServiceImpl implements CrossSellingAiService {

    private static final Long CRM_APP_ID = 3L;
    private static final String AI_CODE = "CROSS_SELLING";

    private final AiSetupService aiSetupService;
    private final OllamaService ollamaService;
    private final ObjectMapper objectMapper;

    @Override
    public CrossSellingAiResponseDto analyze(
            CrossSellingAiRequestDto request
    ) {

        try {

            System.out.println("AI STEP 1 - VALIDATE REQUEST");
            validateRequest(request);

            System.out.println("AI STEP 2 - GET AI SETUP");

            AiSetupDto aiSetup =
                    aiSetupService.getActiveSetup(
                            CRM_APP_ID,
                            AI_CODE
                    );

            System.out.println(
                    "AI SETUP = "
                            + aiSetup.getModelName()
                            + " / "
                            + aiSetup.getApiUrl()
            );

            System.out.println("AI STEP 3 - CREATE MAPPER");

            ObjectMapper snakeCaseMapper =
                    objectMapper.copy()
                            .setPropertyNamingStrategy(
                                    PropertyNamingStrategies.SNAKE_CASE
                            );

            System.out.println("AI STEP 4 - CREATE PROMPT");

            String prompt =
                    snakeCaseMapper.writeValueAsString(request);

            System.out.println("========== AI PROMPT ==========");
            System.out.println(prompt);
            System.out.println("===============================");

            System.out.println("AI STEP 5 - CALL OLLAMA");

            String aiResponse =
                    ollamaService.generate(
                            aiSetup,
                            prompt
                    );

            System.out.println("========== OLLAMA RESPONSE ==========");
            System.out.println(aiResponse);
            System.out.println("=====================================");

            System.out.println("AI STEP 6 - PARSE RESPONSE");

            CrossSellingAiResponseDto result =
                    snakeCaseMapper.readValue(
                            aiResponse,
                            CrossSellingAiResponseDto.class
                    );

            System.out.println("AI STEP 7 - VALIDATE RESPONSE");

            validateAiResponse(
                    request,
                    result
            );

            System.out.println("AI STEP 8 - SUCCESS");

            return result;

        } catch (Exception e) {

            System.err.println("========== CROSS SELLING AI ERROR ==========");
            System.err.println("TYPE    : " + e.getClass().getName());
            System.err.println("MESSAGE : " + e.getMessage());

            Throwable cause = e.getCause();

            while (cause != null) {

                System.err.println(
                        "CAUSED BY: "
                                + cause.getClass().getName()
                                + " -> "
                                + cause.getMessage()
                );

                cause = cause.getCause();
            }

            System.err.println("============================================");

            e.printStackTrace();

            throw new RuntimeException(
                    "Failed to analyze cross selling candidates using AI: "
                            + e.getMessage(),
                    e
            );
        }
    }

    private void validateRequest(
            CrossSellingAiRequestDto request
    ) {

        if (request == null) {
            throw new IllegalArgumentException(
                    "Cross selling AI request cannot be null"
            );
        }

        if (request.getCustomerId() == null) {
            throw new IllegalArgumentException(
                    "Customer ID cannot be null"
            );
        }

        if (request.getCandidateProducts() == null
                || request.getCandidateProducts().isEmpty()) {

            throw new IllegalArgumentException(
                    "Candidate products cannot be empty"
            );
        }
    }

    private void validateAiResponse(
            CrossSellingAiRequestDto request,
            CrossSellingAiResponseDto response
    ) {

        if (response == null) {
            throw new IllegalStateException(
                    "AI response cannot be null"
            );
        }

        if (response.getOpportunities() == null) {
            throw new IllegalStateException(
                    "AI opportunities cannot be null"
            );
        }

        List<CrossSellCandidateDto> candidates =
                request.getCandidateProducts();

        List<CrossSellingOpportunityDto> opportunities =
                response.getOpportunities();

        if (candidates.size() != opportunities.size()) {
            throw new IllegalStateException(
                    "AI returned an invalid number of opportunities"
            );
        }

        Set<Long> expectedRuleIds =
                candidates.stream()
                        .map(CrossSellCandidateDto::getRuleId)
                        .collect(Collectors.toSet());

        Set<Long> returnedRuleIds = new HashSet<>();

        for (CrossSellingOpportunityDto opportunity : opportunities) {

            if (opportunity.getRuleId() == null) {
                throw new IllegalStateException(
                        "AI returned opportunity without ruleId"
                );
            }

            if (!returnedRuleIds.add(opportunity.getRuleId())) {
                throw new IllegalStateException(
                        "AI returned duplicate ruleId: "
                                + opportunity.getRuleId()
                );
            }

            if (!expectedRuleIds.contains(opportunity.getRuleId())) {
                throw new IllegalStateException(
                        "AI returned unknown ruleId: "
                                + opportunity.getRuleId()
                );
            }
        }

        if (!returnedRuleIds.equals(expectedRuleIds)) {
            throw new IllegalStateException(
                    "AI response does not contain exactly the requested rules"
            );
        }

        validateTargetLobs(candidates, opportunities);

        validateConfidence(response.getConfidence());
    }

    private void validateTargetLobs(
            List<CrossSellCandidateDto> candidates,
            List<CrossSellingOpportunityDto> opportunities
    ) {

        Map<Long, CrossSellCandidateDto> candidateMap =
                candidates.stream()
                        .collect(
                                Collectors.toMap(
                                        CrossSellCandidateDto::getRuleId,
                                        Function.identity()
                                )
                        );

        for (CrossSellingOpportunityDto opportunity : opportunities) {

            CrossSellCandidateDto candidate =
                    candidateMap.get(opportunity.getRuleId());

            if (candidate == null) {
                throw new IllegalStateException(
                        "Candidate not found for ruleId: "
                                + opportunity.getRuleId()
                );
            }

            if (!candidate.getTargetLob()
                    .equals(opportunity.getTargetLob())) {

                throw new IllegalStateException(
                        "AI changed targetLob for ruleId: "
                                + opportunity.getRuleId()
                );
            }
        }
    }

    private void validateConfidence(Double confidence) {

        if (confidence == null
                || confidence < 0.0
                || confidence > 1.0) {

            throw new IllegalStateException(
                    "AI returned invalid confidence value"
            );
        }
    }
}
