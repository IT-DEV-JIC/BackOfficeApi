package com.jicjo.apis.service.core;

import com.jicjo.apis.dto.core.AiSetupDto;
import org.springframework.stereotype.Service;

@Service
public interface OllamaService {
    String generate(AiSetupDto aiSetup, String prompt);
}
