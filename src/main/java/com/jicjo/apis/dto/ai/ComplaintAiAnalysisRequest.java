package com.jicjo.apis.dto.ai;

public record ComplaintAiAnalysisRequest(
        Long cstCmpId,
        String title,
        String body,
        String language,
        Long insuranceType,
        Long complaintType
) {
}
