package com.jicjo.apis.controller.ai;

import com.jicjo.apis.dto.ai.AiChatRequest;
import com.jicjo.apis.dto.ai.AiChatResponse;
import com.jicjo.apis.service.ai.AiChatBotService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ai")
public class AiChatBotController {
    private final AiChatBotService aiChatBotService;

    public AiChatBotController(AiChatBotService aiChatBotService) {
        this.aiChatBotService = aiChatBotService;
    }

    @PostMapping("/chat")
    public AiChatResponse chat(@RequestBody AiChatRequest request) {
        return aiChatBotService.chat(request);
    }
}
