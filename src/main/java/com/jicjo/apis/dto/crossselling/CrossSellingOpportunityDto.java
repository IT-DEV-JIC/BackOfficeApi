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
public class CrossSellingOpportunityDto {

    private Long ruleId;
    private String targetLob;
    private String relevance;
    private String reasonAr;
    private List<String> missingInformation;
    private String suggestedEmployeeQuestion;
}
