package com.jicjo.apis.service.crossselling;

import com.jicjo.apis.dto.crossselling.CrossSellCandidateDto;
import com.jicjo.apis.dto.crossselling.CsOpportunityDto;
import com.jicjo.apis.dto.crossselling.CsOpportunityFollowupDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface CrossSellingService {

    // =========================
    // Candidates
    // =========================

    List<CrossSellCandidateDto> getCandidates();


    // =========================
    // Opportunities
    // =========================

    List<CsOpportunityDto> getOpportunities();

    CsOpportunityDto getOpportunity(Long opportunityId);

    List<CsOpportunityDto> getOpportunitiesByCustomer(Long customerId);

    CsOpportunityDto createOpportunity(
            Long customerId,
            Long ruleId,
            BigDecimal expectedPremium,
            String assignedTo,
            String notes,
            String createdBy
    );


    // =========================
    // Followups
    // =========================

    List<CsOpportunityFollowupDto> getFollowups(
            Long opportunityId
    );

    CsOpportunityFollowupDto addFollowup(
            Long opportunityId,
            String actionType,
            String notes,
            Date nextFollowupDate,
            String createdBy
    );


    // =========================
    // Status
    // =========================

    CsOpportunityDto updateStatus(
            Long opportunityId,
            String status,
            String notes,
            String updatedBy,
            BigDecimal actualPremium,
            String wonPolicySource,
            Long wonPolicyId,
            String wonPolicyNo
    );
}
