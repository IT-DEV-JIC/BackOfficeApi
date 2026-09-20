package com.jicjo.apis.dto.crossselling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CsOpportunityDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String opportunityNo;
    private Long customerId;
    private Long ruleId;
    private String sourceLob;
    private String targetLob;
    private String opportunityType;
    private String status;
    private Integer priority;
    private String assignedTo;
    private BigDecimal expectedPremium;
    private BigDecimal actualPremium;
    private String wonPolicySource;
    private Long wonPolicyId;
    private String wonPolicyNo;
    private String notes;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private String closedBy;
    private Date closedDate;

    private String customerNo;
    private String customerNameEn;
    private String customerNameAr;
}
