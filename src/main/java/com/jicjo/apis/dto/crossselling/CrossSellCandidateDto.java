package com.jicjo.apis.dto.crossselling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrossSellCandidateDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long customerId;
    private String customerNo;
    private String customerNameEn;
    private String customerNameAr;
    private String customerType;

    private Long ruleId;
    private String ruleName;

    private String sourceLob;
    private String targetLob;

    private BigDecimal minPremium;
    private Integer minPolicyCount;
    private Integer priority;

    private Integer policyCount;
    private BigDecimal totalGrossPremiumLc;

    private Integer hasSourceLob;
    private Integer hasTargetLob;
}
