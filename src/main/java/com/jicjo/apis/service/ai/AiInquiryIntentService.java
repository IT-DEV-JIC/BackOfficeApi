
package com.jicjo.apis.service.ai;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AiInquiryIntentService {

    Optional<InquiryIntent> detect(
            String message,
            List<QueryCandidate> candidates
    );

    record QueryCandidate(
            String queryCode,
            String queryName,
            String description,
            List<String> keywords
    ) {}

    record InquiryIntent(
            String queryCode,
            Map<String, Object> parameters
    ) {}
}
