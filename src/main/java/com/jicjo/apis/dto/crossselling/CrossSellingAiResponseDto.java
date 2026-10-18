package com.jicjo.apis.dto.crossselling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrossSellingAiResponseDto {

    private String customerSummary;
    private List<CrossSellingOpportunityDto> opportunities;
    private String bestNextAction;
    private List<String> dataQualityNotes;
    private Double confidence;
}
