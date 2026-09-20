package com.jicjo.apis.service.crossselling.impl;

import com.jicjo.apis.dto.crossselling.CrossSellCandidateDto;
import com.jicjo.apis.dto.crossselling.CsCustomerInfoDto;
import com.jicjo.apis.dto.crossselling.CsOpportunityDto;
import com.jicjo.apis.dto.crossselling.CsOpportunityFollowupDto;
import com.jicjo.apis.mapper.crossselling.CsOpportunityFollowupMapper;
import com.jicjo.apis.mapper.crossselling.CsOpportunityMapper;
import com.jicjo.apis.model.crossselling.CsOpportunity;
import com.jicjo.apis.model.crossselling.CsOpportunityFollowup;
import com.jicjo.apis.repository.crossselling.CrossSellCandidateRepository;
import com.jicjo.apis.repository.crossselling.CsCustomerRepository;
import com.jicjo.apis.repository.crossselling.CsOpportunityFollowupReopsitory;
import com.jicjo.apis.repository.crossselling.CsOpportunityRepository;
import com.jicjo.apis.service.crossselling.CrossSellingService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CrossSellingServiceImpl implements CrossSellingService {

    private final CrossSellCandidateRepository candidateRepository;

    private final CsOpportunityRepository opportunityRepository;

    private final CsOpportunityFollowupReopsitory followupRepository;


    // =========================================================
    // OPEN STATUSES
    // =========================================================

    private static final List<String> OPEN_STATUSES = List.of(
            "NEW",
            "ASSIGNED",
            "CONTACTED",
            "INTERESTED",
            "QUOTATION",
            "NEGOTIATION"
    );


    // =========================================================
    // VALID STATUSES
    // =========================================================

    private static final List<String> VALID_STATUSES = List.of(
            "NEW",
            "ASSIGNED",
            "CONTACTED",
            "INTERESTED",
            "QUOTATION",
            "NEGOTIATION",
            "WON",
            "LOST",
            "NOT_INTERESTED"
    );

    private final CsCustomerRepository csCustomerRepository;

    // =========================================================
    // CANDIDATES
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<CrossSellCandidateDto> getCandidates() {

        return candidateRepository.findAllCandidates();
    }


    // =========================================================
    // OPPORTUNITIES
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<CsOpportunityDto> getOpportunities() {

        List<CsOpportunityDto> opportunities =
                CsOpportunityMapper.toCsOpportunityDtoList(
                        opportunityRepository.findAll()
                );

        fillCustomerInfo(opportunities);

        return opportunities;
    }


    @Override
    @Transactional(readOnly = true)
    public CsOpportunityDto getOpportunity(Long opportunityId) {

        CsOpportunity opportunity =
                opportunityRepository.findById(opportunityId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Opportunity not found: " + opportunityId
                                )
                        );

        CsOpportunityDto dto =
                CsOpportunityMapper.toCsOpportunityDto(opportunity);

        fillCustomerInfo(dto);

        return dto;
    }


    @Override
    @Transactional(readOnly = true)
    public List<CsOpportunityDto> getOpportunitiesByCustomer(Long customerId) {

        List<CsOpportunity> opportunities =
                opportunityRepository.findByCustomerIdOrderByCreatedDateDesc(
                        customerId
                );

        List<CsOpportunityDto> result =
                CsOpportunityMapper.toCsOpportunityDtoList(opportunities);

        fillCustomerInfo(result);

        return result;
    }


    // =========================================================
    // CREATE OPPORTUNITY
    // =========================================================

    @Override
    public CsOpportunityDto createOpportunity(
            Long customerId,
            Long ruleId,
            BigDecimal expectedPremium,
            String assignedTo,
            String notes,
            String createdBy
    ) {

        if (customerId == null) {
            throw new RuntimeException("Customer ID is required");
        }

        if (ruleId == null) {
            throw new RuntimeException("Rule ID is required");
        }

        if (createdBy == null || createdBy.trim().isEmpty()) {
            throw new RuntimeException("Created By is required");
        }


        CrossSellCandidateDto candidate =
                candidateRepository.findCandidate(
                        customerId,
                        ruleId
                );


        if (candidate == null) {
            throw new RuntimeException(
                    "Cross sell candidate not found " +
                            "for Customer ID: " + customerId +
                            " and Rule ID: " + ruleId
            );
        }


        if (
                candidate.getTargetLob() == null ||
                        candidate.getTargetLob().trim().isEmpty()
        ) {
            throw new RuntimeException(
                    "Target LOB is missing for cross sell candidate"
            );
        }


        boolean opportunityExists =
                opportunityRepository
                        .existsByCustomerIdAndTargetLobAndStatusIn(
                                customerId,
                                candidate.getTargetLob(),
                                OPEN_STATUSES
                        );


        if (opportunityExists) {
            throw new RuntimeException(
                    "Open opportunity already exists " +
                            "for Customer ID: " + customerId +
                            " and Target LOB: " +
                            candidate.getTargetLob()
            );
        }


        /*
         * ID لا نجيبه من هون.
         * Hibernate سيولده من CS_OPPORTUNITIES_SEQ
         * حسب @GeneratedValue الموجود بالـEntity.
         */


        Long opportunityNoSeq =
                opportunityRepository.getNextOpportunityNo();


        if (opportunityNoSeq == null) {
            throw new RuntimeException(
                    "Could not generate opportunity number"
            );
        }


        CsOpportunity opportunity =
                new CsOpportunity();


        /*
         * لا تعمل:
         * opportunity.setId(...)
         *
         * خلي ID = null
         * Hibernate سيولده وقت save().
         */


        opportunity.setOpportunityNo(
                generateOpportunityNo(
                        opportunityNoSeq
                )
        );


        opportunity.setCustomerId(
                candidate.getCustomerId()
        );


        opportunity.setRuleId(
                candidate.getRuleId()
        );


        opportunity.setSourceLob(
                candidate.getSourceLob()
        );


        opportunity.setTargetLob(
                candidate.getTargetLob()
        );


        opportunity.setOpportunityType(
                "CROSS_SELL"
        );


        opportunity.setPriority(
                candidate.getPriority()
        );


        opportunity.setExpectedPremium(
                expectedPremium
        );


        opportunity.setAssignedTo(
                assignedTo
        );


        opportunity.setNotes(
                notes
        );


        if (
                assignedTo != null &&
                        !assignedTo.trim().isEmpty()
        ) {

            opportunity.setStatus(
                    "ASSIGNED"
            );

        } else {

            opportunity.setStatus(
                    "NEW"
            );
        }


        opportunity.setCreatedBy(
                createdBy
        );


        opportunity.setCreatedDate(
                new Date()
        );


        CsOpportunity savedOpportunity =
                opportunityRepository.save(
                        opportunity
                );


        return CsOpportunityMapper
                .toCsOpportunityDto(
                        savedOpportunity
                );
    }

    // =========================================================
    // FOLLOWUPS
    // =========================================================

    @Override
    @Transactional(readOnly = true)
    public List<CsOpportunityFollowupDto> getFollowups(
            Long opportunityId
    ) {

        // نتأكد إن الـOpportunity موجودة
        findOpportunity(
                opportunityId
        );


        List<CsOpportunityFollowup> followups =
                followupRepository
                        .findByOpportunityIdOrderByFollowupDateDesc(
                                opportunityId
                        );


        return CsOpportunityFollowupMapper
                .toCsOpportunityFollowupDtoList(
                        followups
                );
    }


    // =========================================================
    // ADD FOLLOWUP
    // =========================================================

    @Override
    public CsOpportunityFollowupDto addFollowup(
            Long opportunityId,
            String actionType,
            String notes,
            Date nextFollowupDate,
            String createdBy
    ) {

        // =====================================================
        // 1. GET OPPORTUNITY
        // =====================================================

        CsOpportunity opportunity =
                findOpportunity(
                        opportunityId
                );


        // =====================================================
        // 2. CLOSED CHECK
        // =====================================================

        if (
                isClosed(
                        opportunity.getStatus()
                )
        ) {

            throw new RuntimeException(
                    "Cannot add followup to closed opportunity"
            );
        }


        // =====================================================
        // 3. VALIDATION
        // =====================================================

        if (
                actionType == null ||
                        actionType.trim().isEmpty()
        ) {

            throw new RuntimeException(
                    "Action type is required"
            );
        }


        if (
                createdBy == null ||
                        createdBy.trim().isEmpty()
        ) {

            throw new RuntimeException(
                    "Created By is required"
            );
        }


        // =====================================================
        // 4. CREATE FOLLOWUP
        // =====================================================

        CsOpportunityFollowup followup =
                new CsOpportunityFollowup();


        /*
         * لا نجيب ID من الـ Repository
         * ولا نعمل followup.setId(...)
         *
         * Hibernate سيولد الـ ID من
         * CS_OPPORTUNITY_FOLLOWUPS_SEQ
         * حسب @GeneratedValue الموجود في Entity.
         */


        followup.setOpportunityId(
                opportunityId
        );


        followup.setActionType(
                actionType.trim().toUpperCase()
        );


        followup.setNotes(
                notes
        );


        followup.setFollowupDate(
                new Date()
        );


        followup.setNextFollowupDate(
                nextFollowupDate
        );


        followup.setCreatedBy(
                createdBy
        );


        followup.setCreatedDate(
                new Date()
        );


        // =====================================================
        // 5. SAVE FOLLOWUP
        // =====================================================

        CsOpportunityFollowup savedFollowup =
                followupRepository.save(
                        followup
                );


        // =====================================================
        // 6. AUTO CHANGE STATUS TO CONTACTED
        // =====================================================

        if (
                "NEW".equalsIgnoreCase(
                        opportunity.getStatus()
                )
                        ||
                        "ASSIGNED".equalsIgnoreCase(
                                opportunity.getStatus()
                        )
        ) {

            opportunity.setStatus(
                    "CONTACTED"
            );


            opportunity.setUpdatedBy(
                    createdBy
            );


            opportunity.setUpdatedDate(
                    new Date()
            );


            opportunityRepository.save(
                    opportunity
            );
        }


        return CsOpportunityFollowupMapper
                .toCsOpportunityFollowupDto(
                        savedFollowup
                );
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================

    @Override
    public CsOpportunityDto updateStatus(
            Long opportunityId,
            String status,
            String notes,
            String updatedBy,
            BigDecimal actualPremium,
            String wonPolicySource,
            Long wonPolicyId,
            String wonPolicyNo
    ) {

        // =====================================================
        // 1. GET OPPORTUNITY
        // =====================================================

        CsOpportunity opportunity =
                findOpportunity(
                        opportunityId
                );


        // =====================================================
        // 2. VALIDATION
        // =====================================================

        if (
                status == null ||
                        status.trim().isEmpty()
        ) {

            throw new RuntimeException(
                    "Status is required"
            );
        }


        if (
                updatedBy == null ||
                        updatedBy.trim().isEmpty()
        ) {

            throw new RuntimeException(
                    "Updated By is required"
            );
        }


        String newStatus =
                status
                        .trim()
                        .toUpperCase();


        if (
                !VALID_STATUSES.contains(
                        newStatus
                )
        ) {

            throw new RuntimeException(
                    "Invalid opportunity status: "
                            + newStatus
            );
        }


        // =====================================================
        // 3. STATUS
        // =====================================================

        opportunity.setStatus(
                newStatus
        );


        opportunity.setUpdatedBy(
                updatedBy
        );


        opportunity.setUpdatedDate(
                new Date()
        );


        if (
                notes != null &&
                        !notes.trim().isEmpty()
        ) {

            opportunity.setNotes(
                    notes
            );
        }


        // =====================================================
        // 4. WON
        // =====================================================

        if (
                "WON".equals(
                        newStatus
                )
        ) {

            opportunity.setActualPremium(
                    actualPremium
            );


            opportunity.setWonPolicySource(
                    wonPolicySource
            );


            opportunity.setWonPolicyId(
                    wonPolicyId
            );


            opportunity.setWonPolicyNo(
                    wonPolicyNo
            );


            opportunity.setClosedBy(
                    updatedBy
            );


            opportunity.setClosedDate(
                    new Date()
            );
        }


        // =====================================================
        // 5. LOST / NOT INTERESTED
        // =====================================================

        else if (
                "LOST".equals(
                        newStatus
                )
                        ||
                        "NOT_INTERESTED".equals(
                                newStatus
                        )
        ) {

            opportunity.setClosedBy(
                    updatedBy
            );


            opportunity.setClosedDate(
                    new Date()
            );
        }


        // =====================================================
        // 6. OPEN STATUS
        // =====================================================

        else {

            opportunity.setClosedBy(
                    null
            );


            opportunity.setClosedDate(
                    null
            );
        }


        // =====================================================
        // 7. SAVE
        // =====================================================

        CsOpportunity savedOpportunity =
                opportunityRepository.save(
                        opportunity
                );


        return CsOpportunityMapper
                .toCsOpportunityDto(
                        savedOpportunity
                );
    }


    // =========================================================
    // FIND OPPORTUNITY
    // =========================================================

    private CsOpportunity findOpportunity(
            Long opportunityId
    ) {

        if (opportunityId == null) {

            throw new RuntimeException(
                    "Opportunity ID is required"
            );
        }


        return opportunityRepository
                .findById(
                        opportunityId
                )
                .orElseThrow(
                        () ->
                                new RuntimeException(
                                        "Opportunity not found: "
                                                + opportunityId
                                )
                );
    }


    // =========================================================
    // CLOSED STATUS
    // =========================================================

    private boolean isClosed(
            String status
    ) {

        return "WON".equalsIgnoreCase(
                status
        )
                ||
                "LOST".equalsIgnoreCase(
                        status
                )
                ||
                "NOT_INTERESTED".equalsIgnoreCase(
                        status
                );
    }


    // =========================================================
    // GENERATE OPPORTUNITY NUMBER
    // =========================================================

    private String generateOpportunityNo(
            Long opportunityNoSeq
    ) {

        String yearMonth =
                LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyyMM"
                                )
                        );


        return "CS-"
                + yearMonth
                + "-"
                + String.format(
                "%06d",
                opportunityNoSeq
        );
    }

    private void fillCustomerInfo(List<CsOpportunityDto> opportunities) {

        if (opportunities == null || opportunities.isEmpty()) {
            return;
        }

        List<Long> customerIds = opportunities.stream()
                .map(CsOpportunityDto::getCustomerId)
                .filter(customerId -> customerId != null)
                .distinct()
                .toList();

        if (customerIds.isEmpty()) {
            return;
        }

        List<CsCustomerInfoDto> customers =
                csCustomerRepository.findByCustomerIds(customerIds);

        Map<Long, CsCustomerInfoDto> customerMap =
                customers.stream()
                        .collect(Collectors.toMap(
                                CsCustomerInfoDto::getCustomerId,
                                Function.identity()
                        ));

        for (CsOpportunityDto opportunity : opportunities) {

            CsCustomerInfoDto customer =
                    customerMap.get(opportunity.getCustomerId());

            if (customer == null) {
                continue;
            }

            opportunity.setCustomerNo(
                    customer.getCustomerNo()
            );

            opportunity.setCustomerNameEn(
                    customer.getCustomerNameEn()
            );

            opportunity.setCustomerNameAr(
                    customer.getCustomerNameAr()
            );
        }
    }

    private void fillCustomerInfo(CsOpportunityDto opportunity) {

        if (opportunity == null || opportunity.getCustomerId() == null) {
            return;
        }

        CsCustomerInfoDto customer =
                csCustomerRepository.findByCustomerId(
                        opportunity.getCustomerId()
                );

        if (customer == null) {
            return;
        }

        opportunity.setCustomerNo(customer.getCustomerNo());
        opportunity.setCustomerNameEn(customer.getCustomerNameEn());
        opportunity.setCustomerNameAr(customer.getCustomerNameAr());
    }
}