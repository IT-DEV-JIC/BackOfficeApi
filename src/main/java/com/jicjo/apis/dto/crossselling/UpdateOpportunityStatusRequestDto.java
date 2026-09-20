package com.jicjo.apis.dto.crossselling;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateOpportunityStatusRequestDto {

    private String status;
    private String notes;
    private String updatedBy;

    private BigDecimal actualPremium;
    private String wonPolicySource;
    private Long wonPolicyId;
    private String wonPolicyNo;
}
