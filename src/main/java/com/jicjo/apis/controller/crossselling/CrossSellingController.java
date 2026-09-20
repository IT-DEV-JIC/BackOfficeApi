package com.jicjo.apis.controller.crossselling;


import com.jicjo.apis.dto.crossselling.*;
import com.jicjo.apis.service.crossselling.CrossSellingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cross-selling")
@RequiredArgsConstructor
public class CrossSellingController {
    private final CrossSellingService crossSellingService;


    // ==========================================
    // Candidates
    // ==========================================

    @GetMapping("/candidates")
    public ResponseEntity<List<CrossSellCandidateDto>> getCandidates() {

        return ResponseEntity.ok(
                crossSellingService.getCandidates()
        );
    }


    // ==========================================
    // Opportunities
    // ==========================================

    @GetMapping("/opportunities")
    public ResponseEntity<List<CsOpportunityDto>> getOpportunities() {

        return ResponseEntity.ok(
                crossSellingService.getOpportunities()
        );
    }


    @GetMapping("/opportunities/{id}")
    public ResponseEntity<CsOpportunityDto> getOpportunity(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                crossSellingService.getOpportunity(id)
        );
    }


    @GetMapping("/customers/{customerId}/opportunities")
    public ResponseEntity<List<CsOpportunityDto>>
    getOpportunitiesByCustomer(
            @PathVariable Long customerId
    ) {

        return ResponseEntity.ok(
                crossSellingService
                        .getOpportunitiesByCustomer(customerId)
        );
    }


    @PostMapping("/opportunities")
    public ResponseEntity<CsOpportunityDto> createOpportunity(
            @RequestBody CreateOpportunityRequestDto request
    ) {

        CsOpportunityDto response =
                crossSellingService.createOpportunity(
                        request.getCustomerId(),
                        request.getRuleId(),
                        request.getExpectedPremium(),
                        request.getAssignedTo(),
                        request.getNotes(),
                        request.getCreatedBy()
                );

        return ResponseEntity.ok(response);
    }


    // ==========================================
    // Followups
    // ==========================================

    @GetMapping("/opportunities/{id}/followups")
    public ResponseEntity<List<CsOpportunityFollowupDto>>
    getFollowups(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                crossSellingService.getFollowups(id)
        );
    }


    @PostMapping("/opportunities/{id}/followups")
    public ResponseEntity<CsOpportunityFollowupDto>
    addFollowup(
            @PathVariable Long id,
            @RequestBody CreateFollowupRequestDto request
    ) {

        CsOpportunityFollowupDto response =
                crossSellingService.addFollowup(
                        id,
                        request.getActionType(),
                        request.getNotes(),
                        request.getNextFollowupDate(),
                        request.getCreatedBy()
                );

        return ResponseEntity.ok(response);
    }


    // ==========================================
    // Status
    // ==========================================

    @PutMapping("/opportunities/{id}/status")
    public ResponseEntity<CsOpportunityDto>
    updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateOpportunityStatusRequestDto request
    ) {

        CsOpportunityDto response =
                crossSellingService.updateStatus(
                        id,
                        request.getStatus(),
                        request.getNotes(),
                        request.getUpdatedBy(),
                        request.getActualPremium(),
                        request.getWonPolicySource(),
                        request.getWonPolicyId(),
                        request.getWonPolicyNo()
                );

        return ResponseEntity.ok(response);
    }
}
