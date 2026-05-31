package com.jicjo.apis.mapper.compliance;

import com.jicjo.apis.dto.compliance.CstComplaintFollowupDto;
import com.jicjo.apis.model.compliance.CstComplaintFollowup;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CstComplaintFollowupMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static CstComplaintFollowupDto toCstComplaintFollowupDto(CstComplaintFollowup cstComplaintFollowup){
        return new CstComplaintFollowupDto(
                cstComplaintFollowup.getCstCflId(),
                cstComplaintFollowup.getCstCmpId(),
                cstComplaintFollowup.getCstCflNote(),
                cstComplaintFollowup.getCstCflStatusBefore(),
                cstComplaintFollowup.getCstCflStatusAfter(),
                cstComplaintFollowup.getCstCflActionType(),
                cstComplaintFollowup.getCstCflAssignedTo(),
                cstComplaintFollowup.getCstCflCreatedBy(),
                cstComplaintFollowup.getCstCflCreatedDate(),
                cstComplaintFollowup.getCstCflRequestAttachment(),
                cstComplaintFollowup.getCstCflResponcerAttachment(),
                cstComplaintFollowup.getCstCmpPriorityBefore(),
                cstComplaintFollowup.getCstCmpPriorityAfter()
        );
    }

    public static CstComplaintFollowup toCstComplaintFollowup(CstComplaintFollowupDto cstComplaintFollowupDto){
        return new CstComplaintFollowup(
                cstComplaintFollowupDto.getCstCflId(),
                cstComplaintFollowupDto.getCstCmpId(),
                cstComplaintFollowupDto.getCstCflNote(),
                cstComplaintFollowupDto.getCstCflStatusBefore(),
                cstComplaintFollowupDto.getCstCflStatusAfter(),
                cstComplaintFollowupDto.getCstCflActionType(),
                cstComplaintFollowupDto.getCstCflAssignedTo(),
                cstComplaintFollowupDto.getCstCflCreatedBy(),
                cstComplaintFollowupDto.getCstCflCreatedDate(),
                cstComplaintFollowupDto.getCstCflRequestAttachment(),
                cstComplaintFollowupDto.getCstCflResponcerAttachment(),
                cstComplaintFollowupDto.getCstCmpPriorityBefore(),
                cstComplaintFollowupDto.getCstCmpPriorityAfter()
        );
    }

    public static List<CstComplaintFollowupDto> toCstComplaintFollowupDtoList(List<CstComplaintFollowup> cstComplaintFollowup) {
        return cstComplaintFollowup.stream()
                .map(CstComplaintFollowupMapper::toCstComplaintFollowupDto)
                .collect(Collectors.toList());
    }
}
