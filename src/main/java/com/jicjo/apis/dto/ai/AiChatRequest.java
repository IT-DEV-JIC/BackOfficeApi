package com.jicjo.apis.dto.ai;

public record AiChatRequest(
        String message,
        String language
) {
}
