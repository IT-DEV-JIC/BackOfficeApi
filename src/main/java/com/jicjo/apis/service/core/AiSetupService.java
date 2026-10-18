package com.jicjo.apis.service.core;

import com.jicjo.apis.dto.core.AiSetupDto;
import org.springframework.stereotype.Service;

@Service
public interface AiSetupService {
    AiSetupDto getActiveSetup(Long appId, String aiCode);
}
