package com.jicjo.apis.mapper.compliance;


import com.jicjo.apis.dto.compliance.EmpCompliancesDto;
import com.jicjo.apis.model.compliance.EmpCompliances;

import java.io.Serial;
import java.io.Serializable;

public class EmpCompliancesMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static EmpCompliancesDto toEmpCompliancesDto(EmpCompliances empCompliances){
        return new EmpCompliancesDto(
                empCompliances.getEmpCompliancesId(),
                empCompliances.getEmpCompliancesTitle(),
                empCompliances.getEmpCompliancesBody(),
                empCompliances.getEmpCompliancesIp(),
                empCompliances.getEmpCompliancesHost(),
                empCompliances.getEmpCompliancesWindowsUser(),
                empCompliances.getEmpCompliancesRequestUri(),
                empCompliances.getEmpCompliancesCompliancesOn()
        );
    }

    public static EmpCompliances toEmpCompliances(EmpCompliancesDto empCompliancesDto){
        return new EmpCompliances(
                empCompliancesDto.getEmpCompliancesId(),
                empCompliancesDto.getEmpCompliancesTitle(),
                empCompliancesDto.getEmpCompliancesBody(),
                empCompliancesDto.getEmpCompliancesIp(),
                empCompliancesDto.getEmpCompliancesHost(),
                empCompliancesDto.getEmpCompliancesWindowsUser(),
                empCompliancesDto.getEmpCompliancesRequestUri(),
                empCompliancesDto.getEmpCompliancesCompliancesOn()
        );
    }
}
