package com.jicjo.apis.mapper.crossselling;

import com.jicjo.apis.dto.crossselling.CsOpportunityFollowupDto;
import com.jicjo.apis.model.crossselling.CsOpportunityFollowup;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CsOpportunityFollowupMapper implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static CsOpportunityFollowupDto toCsOpportunityFollowupDto(CsOpportunityFollowup csOpportunityFollowup) {
        return new CsOpportunityFollowupDto(
                csOpportunityFollowup.getId(),
                csOpportunityFollowup.getOpportunityId(),
                csOpportunityFollowup.getActionType(),
                csOpportunityFollowup.getNotes(),
                csOpportunityFollowup.getFollowupDate(),
                csOpportunityFollowup.getNextFollowupDate(),
                csOpportunityFollowup.getCreatedBy(),
                csOpportunityFollowup.getCreatedDate()
        );
    }

    public static CsOpportunityFollowup toCsOpportunityFollowup(CsOpportunityFollowupDto csOpportunityFollowupDto) {
        return new CsOpportunityFollowup(
                csOpportunityFollowupDto.getId(),
                csOpportunityFollowupDto.getOpportunityId(),
                csOpportunityFollowupDto.getActionType(),
                csOpportunityFollowupDto.getNotes(),
                csOpportunityFollowupDto.getFollowupDate(),
                csOpportunityFollowupDto.getNextFollowupDate(),
                csOpportunityFollowupDto.getCreatedBy(),
                csOpportunityFollowupDto.getCreatedDate()
        );
    }

    public static List<CsOpportunityFollowupDto> toCsOpportunityFollowupDtoList(List<CsOpportunityFollowup> csOpportunityFollowup) {
        return csOpportunityFollowup.stream()
                .map(CsOpportunityFollowupMapper::toCsOpportunityFollowupDto)
                .collect(Collectors.toList());
    }
}
