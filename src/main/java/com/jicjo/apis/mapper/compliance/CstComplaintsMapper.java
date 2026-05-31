package com.jicjo.apis.mapper.compliance;

import com.jicjo.apis.dto.compliance.CstComplaintsDto;
import com.jicjo.apis.model.compliance.CstComplaints;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CstComplaintsMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static CstComplaintsDto toCstComplaintsDto(CstComplaints cstComplaints){
        return new CstComplaintsDto(
                cstComplaints.getCstCmpId(),
                cstComplaints.getCstCmpNumber(),
                cstComplaints.getCstCmpType(),
                cstComplaints.getCstCmpFirstName(),
                cstComplaints.getCstCmpFatherName(),
                cstComplaints.getCstCmpGrandfatherName(),
                cstComplaints.getCstCmpFamilyName(),
                cstComplaints.getCstCmpPhoneNumber(),
                cstComplaints.getCstCmpEmail(),
                cstComplaints.getCstCmpTitle(),
                cstComplaints.getCstCmpBody(),
                cstComplaints.getCstCmpCreationDate(),
                cstComplaints.getCstCmpUpdateBy(),
                cstComplaints.getCstCmpUpdateDate(),
                cstComplaints.getCstCdoUser(),
                cstComplaints.getCstCmpPriority(),
                cstComplaints.getCstCmpSource(),
                cstComplaints.getCstCmpStatus(),
                cstComplaints.getCstCmpStatusDate(),
                cstComplaints.getCstCmpResponse(),
                cstComplaints.getCstCmpResolution(),
                cstComplaints.getCstCmpAssignmentDate(),
                cstComplaints.getCstCmpStartDate(),
                cstComplaints.getCstCmpEndDate(),
                cstComplaints.getCstCmpCreatedBy(),
                cstComplaints.getCstCmpInsuranceType(),
                cstComplaints.getCstCmpLang()
        );
    }

    public static CstComplaints toCstComplaints(CstComplaintsDto cstComplaintsDto){
        return new CstComplaints(
                cstComplaintsDto.getCstCmpId(),
                cstComplaintsDto.getCstCmpNumber(),
                cstComplaintsDto.getCstCmpType(),
                cstComplaintsDto.getCstCmpFirstName(),
                cstComplaintsDto.getCstCmpFatherName(),
                cstComplaintsDto.getCstCmpGrandfatherName(),
                cstComplaintsDto.getCstCmpFamilyName(),
                cstComplaintsDto.getCstCmpPhoneNumber(),
                cstComplaintsDto.getCstCmpEmail(),
                cstComplaintsDto.getCstCmpTitle(),
                cstComplaintsDto.getCstCmpBody(),
                cstComplaintsDto.getCstCmpCreationDate(),
                cstComplaintsDto.getCstCmpUpdateBy(),
                cstComplaintsDto.getCstCmpUpdateDate(),
                cstComplaintsDto.getCstCdoUser(),
                cstComplaintsDto.getCstCmpPriority(),
                cstComplaintsDto.getCstCmpSource(),
                cstComplaintsDto.getCstCmpStatus(),
                cstComplaintsDto.getCstCmpStatusDate(),
                cstComplaintsDto.getCstCmpResponse(),
                cstComplaintsDto.getCstCmpResolution(),
                cstComplaintsDto.getCstCmpAssignmentDate(),
                cstComplaintsDto.getCstCmpStartDate(),
                cstComplaintsDto.getCstCmpEndDate(),
                cstComplaintsDto.getCstCmpCreatedBy(),
                cstComplaintsDto.getCstCmpInsuranceType(),
                cstComplaintsDto.getCstCmpLang()
        );
    }

    public static List<CstComplaintsDto> toCstComplaintsDtoList(List<CstComplaints> cstComplaints) {
        return cstComplaints.stream()
                .map(CstComplaintsMapper::toCstComplaintsDto)
                .collect(Collectors.toList());
    }
}
