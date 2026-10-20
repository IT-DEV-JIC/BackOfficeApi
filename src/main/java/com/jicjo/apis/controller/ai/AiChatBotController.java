package com.jicjo.apis.controller.ai;

import com.jicjo.apis.dto.ai.AiChatRequest;
import com.jicjo.apis.dto.ai.AiChatResponse;
import com.jicjo.apis.service.ai.AiChatBotService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(
            value = "/export/excel",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    )
    public ResponseEntity<byte[]> exportExcel(
            @RequestParam String conversationId) {

        byte[] excelFile =
                aiChatBotService.exportLastInquiry(conversationId);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"JIC_AI_Report.xlsx\""
                )
                .contentType(
                        MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                        )
                )
                .contentLength(excelFile.length)
                .body(excelFile);
    }
}
