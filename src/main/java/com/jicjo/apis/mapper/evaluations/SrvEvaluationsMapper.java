package com.jicjo.apis.mapper.evaluations;

import com.jicjo.apis.dto.evaluations.SrvEvaluationsDto;
import com.jicjo.apis.model.evaluations.SrvEvaluations;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class SrvEvaluationsMapper implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static SrvEvaluationsDto toSrvEvaluationsDto (SrvEvaluations srvEvaluations) {
        return new SrvEvaluationsDto(
                srvEvaluations.getSrvEvlId(),
                srvEvaluations.getSrvEvlNo(),
                srvEvaluations.getSrvEvlSourceSystem(),
                srvEvaluations.getSrvEvlReferenceType(),
                srvEvaluations.getSrvEvlReferenceNo(),
                srvEvaluations.getSrvEvlServiceType(),
                srvEvaluations.getSrvEvlInsuranceType(),
                srvEvaluations.getSrvEvlEmpId(),
                srvEvaluations.getSrvEvlEmpName(),
                srvEvaluations.getSrvEvlServiceRate(),
                srvEvaluations.getSrvEvlStaffRate(),
                srvEvaluations.getSrvEvlOverallRate(),
                srvEvaluations.getSrvEvlComment(),
                srvEvaluations.getSrvEvlStatus(),
                srvEvaluations.getSrvEvlCreatedBy(),
                srvEvaluations.getSrvEvlCreatedDate()
        );
    }

    public static SrvEvaluations toSrvEvaluations (SrvEvaluationsDto srvEvaluationsDto) {
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
                srvEvaluationsDto.getSrvEvlServiceRate(),
                srvEvaluationsDto.getSrvEvlStaffRate(),
                srvEvaluationsDto.getSrvEvlOverallRate(),
                srvEvaluationsDto.getSrvEvlComment(),
                srvEvaluationsDto.getSrvEvlStatus(),
                srvEvaluationsDto.getSrvEvlCreatedBy(),
                srvEvaluationsDto.getSrvEvlCreatedDate()
        );
    }

    public static List<SrvEvaluationsDto> toSrvEvaluationsDtoList(List<SrvEvaluations> srvEvaluations) {
        return srvEvaluations.stream()
                .map(SrvEvaluationsMapper::toSrvEvaluationsDto)
                .collect(Collectors.toList());
    }
}
