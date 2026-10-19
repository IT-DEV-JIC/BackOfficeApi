
package com.jicjo.apis.service.ai.impl;

import com.jicjo.apis.repository.ai.AiDynamicQueryRepository;
import com.jicjo.apis.repository.ai.AiInquiryRepository;
import com.jicjo.apis.service.ai.AiInquiryIntentService;
import com.jicjo.apis.service.ai.AiInquiryService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AiInquiryServiceImpl implements AiInquiryService {

    private final AiInquiryRepository inquiryRepository;
    private final AiInquiryIntentService intentService;
    private final AiDynamicQueryRepository queryRepository;
    private final AiDynamicQueryExecutor executor;
    private final AiQueryAuthorizationService authorizationService;

    public AiInquiryServiceImpl(
            AiInquiryRepository inquiryRepository,
            AiInquiryIntentService intentService,
            AiDynamicQueryRepository queryRepository,
            AiDynamicQueryExecutor executor, AiQueryAuthorizationService authorizationService) {

        this.inquiryRepository = inquiryRepository;
        this.intentService = intentService;
        this.queryRepository = queryRepository;
        this.executor = executor;
        this.authorizationService = authorizationService;
    }

    @Override
    public Optional<AiInquiryRequest> resolve(String message) {

        var candidates =
                inquiryRepository.findApprovedCandidates();

        if (candidates == null || candidates.isEmpty()) {
            return Optional.empty();
        }

        return intentService.detect(message, candidates)
                .map(intent -> new AiInquiryRequest(
                        intent.queryCode(),
                        intent.parameters()
                ));
    }

    @Override
    public AiInquiryResult execute(AiInquiryRequest request) {

        var query = queryRepository
                .findApproved(request.queryCode())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Query is not approved: "
                                        + request.queryCode()));

        // Verify current user's permissions before SQL execution.
        authorizationService.checkAccess(query.id());

        var definitions =
                queryRepository.findParameters(query.id());

        var rows = executor.execute(
                query.sql(),
                request.parameters(),
                definitions,
                query.maxRows(),
                query.timeoutSeconds()
        );

        return new AiInquiryResult(
                query.queryCode(),
                rows
        );
    }
}
