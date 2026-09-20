package com.jicjo.apis.dto.crossselling;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class CreateOpportunityRequestDto {
    private Long customerId;
    private Long ruleId;
    private BigDecimal expectedPremium;
    private String assignedTo;
    private String notes;
    private String createdBy;
}
