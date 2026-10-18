package com.jicjo.apis.service.crossselling;

import com.jicjo.apis.dto.crossselling.CrossSellingAiRequestDto;
import com.jicjo.apis.dto.crossselling.CrossSellingAiResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface CrossSellingAiService {
    CrossSellingAiResponseDto analyze(
            CrossSellingAiRequestDto request
    );
}
