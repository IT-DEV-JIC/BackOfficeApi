package com.jicjo.apis.mapper.crossselling;

import com.jicjo.apis.dto.crossselling.CsOpportunityDto;
import com.jicjo.apis.model.crossselling.CsOpportunity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CsOpportunityMapper implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static CsOpportunityDto toCsOpportunityDto(CsOpportunity csOpportunity){
        return new CsOpportunityDto(
                csOpportunity.getId(),
                csOpportunity.getOpportunityNo(),
                csOpportunity.getCustomerId(),
                csOpportunity.getRuleId(),
                csOpportunity.getSourceLob(),
                csOpportunity.getTargetLob(),
                csOpportunity.getOpportunityType(),
                csOpportunity.getStatus(),
                csOpportunity.getPriority(),
                csOpportunity.getAssignedTo(),
                csOpportunity.getExpectedPremium(),
                csOpportunity.getActualPremium(),
                csOpportunity.getWonPolicySource(),
                csOpportunity.getWonPolicyId(),
                csOpportunity.getWonPolicyNo(),
                csOpportunity.getNotes(),
                csOpportunity.getCreatedBy(),
                csOpportunity.getCreatedDate(),
                csOpportunity.getUpdatedBy(),
                csOpportunity.getUpdatedDate(),
                csOpportunity.getClosedBy(),
                csOpportunity.getClosedDate(),
                null,
                null,
                null
        );
    }

    public static CsOpportunity toCsOpportunity (CsOpportunityDto csOpportunityDto){
        return new CsOpportunity(
                csOpportunityDto.getId(),
                csOpportunityDto.getOpportunityNo(),
                csOpportunityDto.getCustomerId(),
                csOpportunityDto.getRuleId(),
                csOpportunityDto.getSourceLob(),
                csOpportunityDto.getTargetLob(),
                csOpportunityDto.getOpportunityType(),
                csOpportunityDto.getStatus(),
                csOpportunityDto.getPriority(),
                csOpportunityDto.getAssignedTo(),
                csOpportunityDto.getExpectedPremium(),
                csOpportunityDto.getActualPremium(),
                csOpportunityDto.getWonPolicySource(),
                csOpportunityDto.getWonPolicyId(),
                csOpportunityDto.getWonPolicyNo(),
                csOpportunityDto.getNotes(),
                csOpportunityDto.getCreatedBy(),
                csOpportunityDto.getCreatedDate(),
                csOpportunityDto.getUpdatedBy(),
                csOpportunityDto.getUpdatedDate(),
                csOpportunityDto.getClosedBy(),
                csOpportunityDto.getClosedDate()
        );
    }

    public static List<CsOpportunityDto> toCsOpportunityDtoList(List<CsOpportunity> csOpportunity) {
        return csOpportunity.stream()
                .map(CsOpportunityMapper::toCsOpportunityDto)
                .collect(Collectors.toList());
    }
}
