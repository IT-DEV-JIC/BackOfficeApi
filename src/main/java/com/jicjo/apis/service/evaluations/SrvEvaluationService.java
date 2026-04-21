package com.jicjo.apis.service.evaluations;

import com.jicjo.apis.dto.evaluations.SrvEvaluationDetailsDto;
import com.jicjo.apis.dto.evaluations.SrvEvaluationDto;
import com.jicjo.apis.repository.evaluations.SrvEvaluationsRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface SrvEvaluationService extends Serializable {
    SrvEvaluationDto addSrvEvaluation(SrvEvaluationDto srvEvaluationDto);
    SrvEvaluationDto updateSrvEvaluation(SrvEvaluationDto srvEvaluationDto);
    void deleteSrvEvaluation(long srvEvlId);
}