package com.jicjo.apis.service.icp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jicjo.apis.dto.icp.MedicalMembersDto;
import com.jicjo.apis.dto.icp.MedicalPoliciesDto;
import com.jicjo.apis.dto.icp.PolicyRequestDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
public interface MedicalPoliciesService extends Serializable {
    List<MedicalPoliciesDto> getMedicalPolicies(Long mpdPlcId, Date fromIssueDate, Date toIssueDate);
    List<MedicalMembersDto> getMedicalMembers(Long mpdPlcId);
    String sendMedicalPolicies(Long mpdPlcId);
}
