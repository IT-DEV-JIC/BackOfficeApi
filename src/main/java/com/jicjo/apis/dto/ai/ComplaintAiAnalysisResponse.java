package com.jicjo.apis.dto.ai;

public record ComplaintAiAnalysisResponse(
        String category,
        String sentiment,
        String priority,
        String summary,
        String suggestedReply,
        String language
) {
}
