package com.jicjo.apis.mapper.core;

import com.jicjo.apis.dto.core.AiSetupDto;
import com.jicjo.apis.model.core.AiSetup;

import java.io.Serial;
import java.io.Serializable;

public class AiSetupMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static AiSetupDto toAiSetupDto(AiSetup aiSetup) {
        return new AiSetupDto(
                aiSetup.getAiSetupId(),
                aiSetup.getAppId(),
                aiSetup.getAiCode(),
                aiSetup.getAiName(),
                aiSetup.getAiNameAr(),
                aiSetup.getModelName(),
                aiSetup.getApiUrl(),
                aiSetup.getSystemPrompt(),
                aiSetup.getTemperature(),
                aiSetup.getContextSize(),
                aiSetup.getKeepAlive(),
                aiSetup.getThinkEnabled(),
                aiSetup.getActiveFlag(),
                aiSetup.getAiCreatedBy(),
                aiSetup.getAiCreatedOn(),
                aiSetup.getAiUpdatedBy(),
                aiSetup.getAiUpdatedOn()
        );
    }

    public static AiSetup toAiSetup(AiSetupDto aiSetupDto) {
        return new AiSetup(
                aiSetupDto.getAiSetupId(),
                aiSetupDto.getAppId(),
                aiSetupDto.getAiCode(),
                aiSetupDto.getAiName(),
                aiSetupDto.getAiNameAr(),
                aiSetupDto.getModelName(),
                aiSetupDto.getApiUrl(),
                aiSetupDto.getSystemPrompt(),
                aiSetupDto.getTemperature(),
                aiSetupDto.getContextSize(),
                aiSetupDto.getKeepAlive(),
                aiSetupDto.getThinkEnabled(),
                aiSetupDto.getActiveFlag(),
                aiSetupDto.getAiCreatedBy(),
                aiSetupDto.getAiCreatedOn(),
                aiSetupDto.getAiUpdatedBy(),
                aiSetupDto.getAiUpdatedOn()
        );
    }
}
