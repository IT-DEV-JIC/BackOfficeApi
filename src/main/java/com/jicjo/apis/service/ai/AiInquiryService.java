package com.jicjo.apis.service.ai;

import java.util.Map;
import java.util.Optional;

public interface AiInquiryService {

    Optional<AiInquiryRequest> resolve(String message);

    AiInquiryResult execute(AiInquiryRequest request);

    record AiInquiryRequest(
            String queryCode,
            Map<String, Object> parameters
    ) {}

    record AiInquiryResult(
            String queryCode,
            Object data
    ) {}
}
