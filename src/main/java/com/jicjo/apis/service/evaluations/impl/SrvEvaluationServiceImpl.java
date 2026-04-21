package com.jicjo.apis.service.evaluations.impl;

import com.jicjo.apis.dto.evaluations.SrvEvaluationDto;
import com.jicjo.apis.exception.ResourceNotFoundException;
import com.jicjo.apis.mapper.evaluations.SrvEvaluationsMapper;
import com.jicjo.apis.model.evaluations.SrvEvaluations;
import com.jicjo.apis.repository.evaluations.SrvEvaluationsRepository;
import com.jicjo.apis.service.evaluations.SrvEvaluationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;


@Service

public class SrvEvaluationServiceImpl implements SrvEvaluationService {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    SrvEvaluationsRepository srvEvaluationRepository;

    @Transactional
    @Override
    public SrvEvaluationDto addSrvEvaluation(SrvEvaluationDto srvEvaluationDto) {
        SrvEvaluations srvEvaluation = SrvEvaluationsMapper.toSrvEvaluations(srvEvaluationDto);
        srvEvaluation.setSrvEvlId(null);
        return SrvEvaluationsMapper.toSrvEvaluationsDto(this.srvEvaluationRepository.save(srvEvaluation));
    }
    @Transactional
    @Override
    public SrvEvaluationDto  updateSrvEvaluation(SrvEvaluationDto srvEvaluationDto) {
        SrvEvaluations srvEvaluation = this.srvEvaluationRepository.findById(srvEvaluationDto.getSrvEvlId())
                .orElseThrow(() -> new ResourceNotFoundException("Evaluations Not Found"));

        srvEvaluation.setSrvEvlId(srvEvaluationDto.getSrvEvlId());
        srvEvaluation.setSrvEvlNo(srvEvaluationDto.getSrvEvlNo());
        srvEvaluation.setSrvEvlSourceSystem(srvEvaluationDto.getSrvEvlSourceSystem());
        srvEvaluation.setSrvEvlReferenceType(srvEvaluationDto.getSrvEvlReferenceType());
        srvEvaluation.setSrvEvlReferenceNo(srvEvaluationDto.getSrvEvlReferenceNo());
        srvEvaluation.setSrvEvlServiceType(srvEvaluationDto.getSrvEvlServiceType());
        srvEvaluation.setSrvEvlInsuranceType(srvEvaluationDto.getSrvEvlInsuranceType());
        srvEvaluation.setSrvEvlEmpId(srvEvaluationDto.getSrvEvlEmpId());
        srvEvaluation.setSrvEvlEmpName(srvEvaluationDto.getSrvEvlEmpName());
        srvEvaluation.setSrvEvlDeptId(srvEvaluation.getSrvEvlDeptId());
        srvEvaluation.setSrvEvlDeptName(srvEvaluationDto.getSrvEvlDeptName());
        srvEvaluation.setSrvEvlBranchId(srvEvaluationDto.getSrvEvlBranchId());
        srvEvaluation.setSrvEvlBranchName(srvEvaluationDto.getSrvEvlBranchName());
        srvEvaluation.setSrvEvlCustomerName(srvEvaluationDto.getSrvEvlCustomerName());
        srvEvaluation.setSrvEvlPhoneNo(srvEvaluationDto.getSrvEvlPhoneNo());
        srvEvaluation.setSrvEvlEmail(srvEvaluationDto.getSrvEvlEmail());
        srvEvaluation.setSrvEvlServiceRate(srvEvaluationDto.getSrvEvlServiceRate());
        srvEvaluation.setSrvEvlStaffRate(srvEvaluationDto.getSrvEvlStaffRate());
        srvEvaluation.setSrvEvlOverallRate(srvEvaluationDto.getSrvEvlOverallRate());
        srvEvaluation.setSrvEvlRecommendFlag(srvEvaluationDto.getSrvEvlRecommendFlag());
        srvEvaluation.setSrvEvlComment(srvEvaluationDto.getSrvEvlComment());
        srvEvaluation.setSrvEvlInternalNote(srvEvaluationDto.getSrvEvlInternalNote());
        srvEvaluation.setSrvEvlStatus(srvEvaluationDto.getSrvEvlStatus());
        srvEvaluation.setSrvEvlStatusDate(srvEvaluationDto.getSrvEvlStatusDate());
        srvEvaluation.setSrvEvlCreatedBy(srvEvaluationDto.getSrvEvlCreatedBy());
        srvEvaluation.setSrvEvlCreatedDate(srvEvaluationDto.getSrvEvlCreatedDate());
        srvEvaluation.setSrvEvlUpdatedBy(srvEvaluationDto.getSrvEvlUpdatedBy());
        srvEvaluation.setSrvEvlUpdatedDate(srvEvaluationDto.getSrvEvlUpdatedDate());


        return SrvEvaluationsMapper.toSrvEvaluationsDto(srvEvaluation);
    }

    @Transactional
    @Override

    public void deleteSrvEvaluation(long srvEvdId) {
        SrvEvaluations srvEvaluation = this.srvEvaluationRepository.findById(srvEvdId)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluations Not Found"));

        srvEvaluationRepository.delete(srvEvaluation);
    }
}