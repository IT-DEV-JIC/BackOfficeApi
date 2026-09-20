package com.jicjo.apis.controller.ai;


import com.jicjo.apis.dto.ai.ComplaintAiAnalysisRequest;
import com.jicjo.apis.dto.ai.ComplaintAiAnalysisResponse;
import com.jicjo.apis.service.ai.ComplaintAiService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ai/complaints")
public class ComplaintAiController {
    private final ComplaintAiService complaintAiService;

    public ComplaintAiController(ComplaintAiService complaintAiService) {
        this.complaintAiService = complaintAiService;
    }

    @PostMapping("/analyze")
    public ComplaintAiAnalysisResponse analyzeComplaint(
            @RequestBody ComplaintAiAnalysisRequest request
    ) {
        return complaintAiService.analyzeComplaint(request);
    }
}
