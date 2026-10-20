
package com.jicjo.apis.service.ai.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AiActionDetectionService {

    private final ChatClient chatClient;

    public AiActionDetectionService(
            ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public Action detect(
            String message,
            String previousQuestion) {

        String prompt = """
                Classify the user's requested action.

                Available actions:
                EXPORT_EXCEL
                INQUIRY
                GENERAL

                EXPORT_EXCEL:
                The user wants to export data or previous
                results to an Excel spreadsheet.

                INQUIRY:
                The user asks about internal company data.

                GENERAL:
                All other requests.

                Previous question:
                %s

                Current message:
                %s

                Return exactly one action name.
                No explanation.
                """.formatted(
                previousQuestion == null
                        ? "None" : previousQuestion,
                message
        );

        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        if (response == null) {
            return Action.GENERAL;
        }

        String normalized = response.trim()
                .toUpperCase(Locale.ROOT);

        return switch (normalized) {
            case "EXPORT_EXCEL" -> Action.EXPORT_EXCEL;
            case "INQUIRY" -> Action.INQUIRY;
            default -> Action.GENERAL;
        };
    }

    public enum Action {
        EXPORT_EXCEL,
        INQUIRY,
        GENERAL
    }
}
