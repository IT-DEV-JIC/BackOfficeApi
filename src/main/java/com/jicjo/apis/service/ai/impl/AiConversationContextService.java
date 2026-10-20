
package com.jicjo.apis.service.ai.impl;

import com.jicjo.apis.service.ai.AiInquiryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AiConversationContextService {

    private static final Duration TTL =
            Duration.ofMinutes(60);

    private final Map<ContextKey, StoredInquiry> contexts =
            new ConcurrentHashMap<>();

    public void remember(
            String userId,
            String conversationId,
            String originalQuestion,
            AiInquiryService.AiInquiryRequest request) {

        ContextKey key = new ContextKey(
                userId, conversationId);

        StoredInquiry inquiry = new StoredInquiry(
                originalQuestion,
                request.queryCode(),
                Map.copyOf(request.parameters() == null
                        ? Map.of()
                        : request.parameters()),
                Instant.now()
        );

        contexts.put(key, inquiry);
    }

    public Optional<StoredInquiry> getLastInquiry(
            String userId,
            String conversationId) {

        ContextKey key = new ContextKey(
                userId, conversationId);

        StoredInquiry inquiry = contexts.get(key);

        if (inquiry == null) {
            return Optional.empty();
        }

        if (isExpired(inquiry)) {
            contexts.remove(key);
            return Optional.empty();
        }

        return Optional.of(inquiry);
    }

    public void clear(
            String userId,
            String conversationId) {

        contexts.remove(
                new ContextKey(userId, conversationId));
    }

    @Scheduled(fixedDelay = 600000)
    public void cleanup() {
        contexts.entrySet().removeIf(
                entry -> isExpired(entry.getValue()));
    }

    private boolean isExpired(StoredInquiry inquiry) {
        return Instant.now().isAfter(
                inquiry.createdAt().plus(TTL));
    }

    private record ContextKey(
            String userId,
            String conversationId) {
    }

    public record StoredInquiry(
            String originalQuestion,
            String queryCode,
            Map<String, Object> parameters,
            Instant createdAt) {
    }
}
