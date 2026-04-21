package com.jicjo.apis.service.evaluations;


import com.jicjo.apis.dto.evaluations.SrvEvaluationDetailsDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface SrvEvaluationDetailsService extends Serializable {
   SrvEvaluationDetailsDto addSrvEvaluationDetails(SrvEvaluationDetailsDto srvEvaluationDetailsDto);
   SrvEvaluationDetailsDto updateSrvEvaluationDetails(SrvEvaluationDetailsDto srvEvaluationDetailsDto);
   void deleteSrvEvaluationDetails(long srvEvdId);
}
