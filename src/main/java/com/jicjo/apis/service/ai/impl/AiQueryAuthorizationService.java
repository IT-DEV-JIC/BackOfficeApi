
package com.jicjo.apis.service.ai.impl;

import com.jicjo.apis.repository.ai.AiDynamicQueryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiQueryAuthorizationService {

    private static final Logger log =
            LoggerFactory.getLogger(AiQueryAuthorizationService.class);

    private final AiDynamicQueryRepository repository;

    public AiQueryAuthorizationService(
            AiDynamicQueryRepository repository) {
        this.repository = repository;
    }

    public void checkAccess(Long queryId) {

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        // 1. Check authentication
        if (auth == null
                || !auth.isAuthenticated()
                || auth instanceof AnonymousAuthenticationToken) {

            throw new AccessDeniedException(
                    "Authentication required");
        }

        if (queryId == null) {
            throw new IllegalArgumentException(
                    "Query ID is required");
        }

        // 2. Read query permissions from Oracle
        List<String> queryPermissions =
                repository.findPermissions(queryId);

        if (queryPermissions == null
                || queryPermissions.isEmpty()) {

            log.warn(
                    "AI query permissions not configured: queryId={}",
                    queryId
            );

            throw new AccessDeniedException(
                    "Query permissions are not configured");
        }

        // 3. Validate permission configuration
        boolean hasValidPermission = queryPermissions.stream()
                .anyMatch(permission ->
                        permission != null && !permission.isBlank());

        if (!hasValidPermission) {
            throw new AccessDeniedException(
                    "Invalid query permissions configuration");
        }

        log.info(
                "AI query permission configuration found: user={}, queryId={}, permissions={}",
                auth.getName(),
                queryId,
                queryPermissions
        );

        // NOTE:
        // This checks that the query has configured permissions.
        // It does NOT verify that the authenticated user owns them.
    }
}
