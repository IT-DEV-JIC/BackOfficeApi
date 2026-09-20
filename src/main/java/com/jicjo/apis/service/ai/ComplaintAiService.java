package com.jicjo.apis.service.ai;

import com.jicjo.apis.dto.ai.ComplaintAiAnalysisRequest;
import com.jicjo.apis.dto.ai.ComplaintAiAnalysisResponse;
import org.springframework.stereotype.Service;

@Service
public interface ComplaintAiService {
    ComplaintAiAnalysisResponse analyzeComplaint(ComplaintAiAnalysisRequest request);
}
