package com.jicjo.apis.service.icp.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jicjo.apis.dto.icp.AuthRequestDto;
import com.jicjo.apis.dto.icp.MedicalMembersDto;
import com.jicjo.apis.dto.icp.MedicalPoliciesDto;
import com.jicjo.apis.dto.icp.PolicyRequestDto;
import com.jicjo.apis.repository.icp.MedicalPoliciesRepository;
import com.jicjo.apis.service.core.ApiCollingService;
import com.jicjo.apis.service.icp.MedicalPoliciesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.Date;
import java.util.List;

@Service
public class MedicalPoliciesServiceImpl implements MedicalPoliciesService {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private  ApiCollingService apiCollingService;

    @Autowired
    private MedicalPoliciesRepository medicalPoliciesRepository;

    @Override
    @Transactional
    public List<MedicalPoliciesDto> getMedicalPolicies(Long mpdPlcId, Date fromIssueDate, Date toIssueDate) {
        return medicalPoliciesRepository.getMedicalPolicies(mpdPlcId, fromIssueDate, toIssueDate);
    }

    @Override
    @Transactional
    public List<MedicalMembersDto> getMedicalMembers(Long mpdPlcId) {
        return medicalPoliciesRepository.getMedicalMembers(mpdPlcId);
    }

    @Override
    @Transactional
    public String sendMedicalPolicies(Long mpdPlcId)  {

        List<MedicalPoliciesDto> medicalPoliciesList = medicalPoliciesRepository.getMedicalPolicies(mpdPlcId, null, null);
        MedicalPoliciesDto medicalPoliciesDto = medicalPoliciesList.get(0);

        AuthRequestDto authRequestDto = new AuthRequestDto();
        authRequestDto.setUsername(medicalPoliciesDto.getUSERNAME());
        authRequestDto.setPassword(medicalPoliciesDto.getPASSWORD());

        PolicyRequestDto policyRequestDto = new PolicyRequestDto();
        policyRequestDto.setAuth(authRequestDto);
        policyRequestDto.setInsuranceCompanyCode(medicalPoliciesDto.getINSURANCECOMPANYCODE());
        policyRequestDto.setPolicyNumber(medicalPoliciesDto.getPOLICYNUMBER());
        policyRequestDto.setPolicyIssueDate(medicalPoliciesDto.getPOLICYISSUEDATE());
        policyRequestDto.setPolicyStartDate(medicalPoliciesDto.getPOLICYSTARTDATE());
        policyRequestDto.setPolicyExpiryDate(medicalPoliciesDto.getPOLICYEXPIRYDATE());
        policyRequestDto.setPolicyType(medicalPoliciesDto.getPOLICYTYPE());
        policyRequestDto.setPolicyOwnerType(medicalPoliciesDto.getPOLICYOWNERTYPE());
        policyRequestDto.setPolicyOwnerName(medicalPoliciesDto.getPOLICYOWNERNAME());
        policyRequestDto.setPolicyOwnerID(medicalPoliciesDto.getPOLICYOWNERID());
        policyRequestDto.setPlanName(medicalPoliciesDto.getPLANNAME());
        policyRequestDto.setOperationType(medicalPoliciesDto.getOPERATIONTYPE());

        try {
            return apiCollingService.createIcpPolicy(policyRequestDto);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to create icp policy", e);
        }
    }
}
