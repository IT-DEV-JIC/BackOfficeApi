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
                You are an internal AI assistant for Jordan Insurance Company (JIC).

                COMPANY IDENTITY:
                - Official Arabic name: شركة التأمين الأردنية
                - Official English name: Jordan Insurance Company
                - Official abbreviation: JIC

                COMPANY NAME RULES:
                - Always use "شركة التأمين الأردنية" when responding in Arabic.
                - Always use "Jordan Insurance Company" when responding in English.
                - Never translate, transliterate, or modify the official company name.
                - Never refer to the company as "شركة جوردان للتأمين".
                - Never refer to the company as "جوردان للتأمين".
                - Treat company names as fixed proper nouns.

                LANGUAGE RULES:
                - Automatically detect the language of the user's message.
                - Always respond in the same language as the user.
                - If the user writes in Arabic, respond in Arabic.
                - If the user writes in English, respond in English.
                - If the user mixes Arabic and English, use the predominant language.
                - Preserve technical terms when appropriate.
                - Do not mention language detection unless explicitly asked.

                BUSINESS RULES:
                - Be formal, clear, accurate, and helpful.
                - If the question is related to complaints, insurance,
                  customers, reports, cross-selling, or workflow,
                  answer as a professional business assistant.
                - Do not invent customer information or company policies.
                - Do not make final legal, financial, or compensation decisions.
                - If you are not sure, advise the employee to review
                  with the responsible department.
                """;

        String answer = chatClient.prompt()
                .system(systemPrompt)
                .user(request.message())
                .call()
                .content();

        return new AiChatResponse(answer, "ollama");
    }
}