package com.jicjo.apis.service.core.impl;

import com.jicjo.apis.dto.ai.OllamaGenerateRequest;
import com.jicjo.apis.dto.ai.OllamaGenerateResponse;
import com.jicjo.apis.dto.core.AiSetupDto;
import com.jicjo.apis.service.core.OllamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OllamaServiceImpl implements OllamaService {
    private final RestTemplate restTemplate;

    @Override
    public String generate(AiSetupDto aiSetup, String prompt) {

        String url = aiSetup.getApiUrl() + "/api/generate";

        Map<String, Object> options = new HashMap<>();
        options.put("temperature", aiSetup.getTemperature());
        options.put("num_ctx", aiSetup.getContextSize());

        OllamaGenerateRequest request =
                new OllamaGenerateRequest(
                        aiSetup.getModelName(),
                        aiSetup.getSystemPrompt(),
                        prompt,
                        false,
                        aiSetup.getThinkEnabled() != null
                                && aiSetup.getThinkEnabled() == 1,
                        "json",
                        aiSetup.getKeepAlive(),
                        options
                );

        OllamaGenerateResponse response =
                restTemplate.postForObject(
                        url,
                        request,
                        OllamaGenerateResponse.class
                );

        if (response == null) {
            throw new RuntimeException(
                    "No response received from Ollama"
            );
        }

        if (response.getResponse() == null
                || response.getResponse().isBlank()) {

            throw new RuntimeException(
                    "Empty response received from Ollama"
            );
        }

        return response.getResponse();
    }
}
