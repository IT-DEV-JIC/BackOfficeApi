package com.jicjo.apis.service.core.impl;

import com.jicjo.apis.dto.core.AiSetupDto;
import com.jicjo.apis.mapper.core.AiSetupMapper;
import com.jicjo.apis.model.core.AiSetup;
import com.jicjo.apis.repository.core.AiSetupRepository;
import com.jicjo.apis.service.core.AiSetupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AiSetupServiceImpl implements AiSetupService {

    private final AiSetupRepository aiSetupRepository;

    @Override
    @Transactional(readOnly = true)
    public AiSetupDto getActiveSetup(Long appId, String aiCode) {

        AiSetup aiSetup = aiSetupRepository
                .findByAppIdAndAiCodeAndActiveFlag(
                        appId,
                        aiCode,
                        1
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Active AI setup not found for APP_ID: "
                                        + appId
                                        + ", AI_CODE: "
                                        + aiCode
                        )
                );

        return AiSetupMapper.toAiSetupDto(aiSetup);
    }
}
