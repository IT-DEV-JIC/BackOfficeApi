package com.jicjo.apis.service.compliance.impl;

import com.jicjo.apis.dto.compliance.EmpCompliancesDto;
import com.jicjo.apis.mapper.compliance.EmpCompliancesMapper;
import com.jicjo.apis.model.compliance.EmpCompliances;
import com.jicjo.apis.repository.compliance.EmpCompliancesRepository;
import com.jicjo.apis.service.compliance.EmpCompliancesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;

@Service
public class EmpCompliancesServiceImpl implements EmpCompliancesService {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    EmpCompliancesRepository empCompliancesRepository;


    @Override
    @Transactional
    public void addCompliance(EmpCompliancesDto empCompliancesDto) {
        EmpCompliances empCompliances = EmpCompliancesMapper.toEmpCompliances(empCompliancesDto);

        empCompliances.setEmpCompliancesId(null);
        empCompliancesRepository.save(empCompliances);
    }
}
