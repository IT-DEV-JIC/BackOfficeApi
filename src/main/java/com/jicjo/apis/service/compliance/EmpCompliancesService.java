package com.jicjo.apis.service.compliance;

import com.jicjo.apis.dto.compliance.EmpCompliancesDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface EmpCompliancesService  extends Serializable {
    void addCompliance(EmpCompliancesDto empCompliancesDto);
}
