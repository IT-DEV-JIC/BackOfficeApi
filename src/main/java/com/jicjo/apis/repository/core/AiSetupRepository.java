package com.jicjo.apis.repository.core;

import com.jicjo.apis.model.core.AiSetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AiSetupRepository extends JpaRepository<AiSetup, Long> {
    Optional<AiSetup> findByAppIdAndAiCodeAndActiveFlag(
            Long appId,
            String aiCode,
            Integer activeFlag
    );
}
