package com.jicjo.apis.mapper.evaluations;

import com.jicjo.apis.dto.evaluations.SrvEvaluationDto;
import com.jicjo.apis.model.evaluations.SrvEvaluations;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class SrvEvaluationsMapper implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static SrvEvaluationDto toSrvEvaluationsDto (SrvEvaluations srvEvaluations) {
        return new SrvEvaluationDto(
                srvEvaluations.getSrvEvlId(),
                srvEvaluations.getSrvEvlNo(),
                srvEvaluations.getSrvEvlSourceSystem(),
                srvEvaluations.getSrvEvlReferenceType(),
                srvEvaluations.getSrvEvlReferenceNo(),
                srvEvaluations.getSrvEvlServiceType(),
                srvEvaluations.getSrvEvlInsuranceType(),
                srvEvaluations.getSrvEvlEmpId(),
                srvEvaluations.getSrvEvlEmpName(),
                srvEvaluations.getSrvEvlDeptId(),
                srvEvaluations.getSrvEvlDeptName(),
                srvEvaluations.getSrvEvlBranchId(),
                srvEvaluations.getSrvEvlBranchName(),
                srvEvaluations.getSrvEvlCustomerName(),
                srvEvaluations.getSrvEvlPhoneNo(),
                srvEvaluations.getSrvEvlEmail(),
                srvEvaluations.getSrvEvlServiceRate(),
                srvEvaluations.getSrvEvlStaffRate(),
                srvEvaluations.getSrvEvlOverallRate(),
                srvEvaluations.getSrvEvlRecommendFlag(),
                srvEvaluations.getSrvEvlComment(),
                srvEvaluations.getSrvEvlInternalNote(),
                srvEvaluations.getSrvEvlStatus(),
                srvEvaluations.getSrvEvlStatusDate(),
                srvEvaluations.getSrvEvlCreatedBy(),
                srvEvaluations.getSrvEvlCreatedDate(),
                srvEvaluations.getSrvEvlUpdatedBy(),
                srvEvaluations.getSrvEvlUpdatedDate()
        );
    }

    public static SrvEvaluations toSrvEvaluations (SrvEvaluationDto srvEvaluationsDto) {
        return new SrvEvaluations(
                srvEvaluationsDto.getSrvEvlId(),
                srvEvaluationsDto.getSrvEvlNo(),
                srvEvaluationsDto.getSrvEvlSourceSystem(),
                srvEvaluationsDto.getSrvEvlReferenceType(),
                srvEvaluationsDto.getSrvEvlReferenceNo(),
                srvEvaluationsDto.getSrvEvlServiceType(),
                srvEvaluationsDto.getSrvEvlInsuranceType(),
                srvEvaluationsDto.getSrvEvlEmpId(),
                srvEvaluationsDto.getSrvEvlEmpName(),
                srvEvaluationsDto.getSrvEvlDeptId(),
                srvEvaluationsDto.getSrvEvlDeptName(),
                srvEvaluationsDto.getSrvEvlBranchId(),
                srvEvaluationsDto.getSrvEvlBranchName(),
                srvEvaluationsDto.getSrvEvlCustomerName(),
                srvEvaluationsDto.getSrvEvlPhoneNo(),
                srvEvaluationsDto.getSrvEvlEmail(),
                srvEvaluationsDto.getSrvEvlServiceRate(),
                srvEvaluationsDto.getSrvEvlStaffRate(),
                srvEvaluationsDto.getSrvEvlOverallRate(),
                srvEvaluationsDto.getSrvEvlRecommendFlag(),
                srvEvaluationsDto.getSrvEvlComment(),
                srvEvaluationsDto.getSrvEvlInternalNote(),
                srvEvaluationsDto.getSrvEvlStatus(),
                srvEvaluationsDto.getSrvEvlStatusDate(),
                srvEvaluationsDto.getSrvEvlCreatedBy(),
                srvEvaluationsDto.getSrvEvlCreatedDate(),
                srvEvaluationsDto.getSrvEvlUpdatedBy(),
                srvEvaluationsDto.getSrvEvlUpdatedDate()
        );
    }

    public static List<SrvEvaluationDto> toSrvEvaluationsDtoList(List<SrvEvaluations> srvEvaluations) {
        return srvEvaluations.stream()
                .map(SrvEvaluationsMapper::toSrvEvaluationsDto)
                .collect(Collectors.toList());
    }
}
