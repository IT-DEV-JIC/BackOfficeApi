package com.jicjo.apis.service.ai;

import com.jicjo.apis.dto.ai.AiChatRequest;
import com.jicjo.apis.dto.ai.AiChatResponse;
import org.springframework.stereotype.Service;

@Service
public interface AiChatBotService {
    AiChatResponse chat(AiChatRequest request);
}
