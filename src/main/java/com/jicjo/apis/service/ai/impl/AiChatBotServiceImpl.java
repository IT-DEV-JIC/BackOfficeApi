package com.jicjo.apis.service.ai.impl;

import com.jicjo.apis.dto.ai.AiChatRequest;
import com.jicjo.apis.dto.ai.AiChatResponse;
import com.jicjo.apis.service.ai.AiChatBotService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiChatBotServiceImpl implements AiChatBotService {

    private final ChatClient chatClient;

    public AiChatBotServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public AiChatResponse chat(AiChatRequest request) {

        String systemPrompt = """
                You are an internal AI assistant for Jordan Insurance Company.
                
                Rules:
                - Answer in the same language as the user.
                - Be formal, clear, and helpful.
                - If the question is related to complaints, insurance, customers, reports, or workflow, answer as a business assistant.
                - Do not make final legal, financial, or compensation decisions.
                - If you are not sure, say that the employee should review with the responsible department.
                """;

        String answer = chatClient.prompt()
                .system(systemPrompt)
                .user(request.message())
                .call()
                .content();

        return new AiChatResponse(answer, "ollama");
    }
}
