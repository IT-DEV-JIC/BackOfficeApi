package com.jicjo.apis.service.evaluations.impl;


import com.jicjo.apis.dto.evaluations.SrvEvaluationDetailsDto;
import com.jicjo.apis.exception.ResourceNotFoundException;
import com.jicjo.apis.mapper.evaluations.SrvEvaluationsDetailsMapper;
import com.jicjo.apis.model.evaluations.SrvEvaluationDetails;
import com.jicjo.apis.repository.evaluations.SrvEvaluationDetailsRepository;
import com.jicjo.apis.service.evaluations.SrvEvaluationDetailsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;


@Service
public class SrvEvaluationDetailsServiceImpl implements SrvEvaluationDetailsService {

    @Serial
    private static final long serialVersionUID = 1L;

@Autowired
    SrvEvaluationDetailsRepository srvEvaluationDetailsRepository;

    @Transactional
    @Override
    public SrvEvaluationDetailsDto addSrvEvaluationDetails(SrvEvaluationDetailsDto srvEvaluationDetailsDto) {
         SrvEvaluationDetails srvEvaluationDetails = SrvEvaluationsDetailsMapper.srvEvaluationDetailsDto(srvEvaluationDetailsDto);
         srvEvaluationDetails.setSrvEvdId(null);
         return SrvEvaluationsDetailsMapper.srvEvaluationDetailsDto(this.srvEvaluationDetailsRepository.save(srvEvaluationDetails));
    }
    @Transactional
    @Override
public SrvEvaluationDetailsDto  updateSrvEvaluationDetails(SrvEvaluationDetailsDto srvEvaluationDetailsDto) {
        SrvEvaluationDetails srvEvaluationDetails = this.srvEvaluationDetailsRepository.findById(srvEvaluationDetailsDto.getSrvEvdId())
                .orElseThrow(() -> new ResourceNotFoundException("Evaluations Not Found"));

        srvEvaluationDetails.setSrvEvdId(srvEvaluationDetailsDto.getSrvEvdId());
        srvEvaluationDetails.setSrvEvlId(srvEvaluationDetailsDto.getSrvEvlId());
        srvEvaluationDetails.setSrvEvdQuestionCode(srvEvaluationDetailsDto.getSrvEvdQuestionCode());
        srvEvaluationDetails.setSrvEvdQuestionAr(srvEvaluationDetailsDto.getSrvEvdQuestionAr());
        srvEvaluationDetails.setSrvEvdQuestionEn(srvEvaluationDetailsDto.getSrvEvdQuestionEn());
        srvEvaluationDetails.setSrvEvdAnswerType(srvEvaluationDetailsDto.getSrvEvdAnswerType());
        srvEvaluationDetails.setSrvEvdRateValue(srvEvaluationDetailsDto.getSrvEvdRateValue());
        srvEvaluationDetails.setSrvEvdTextValue(srvEvaluationDetailsDto.getSrvEvdTextValue());

        return SrvEvaluationsDetailsMapper.srvEvaluationDetailsDto(srvEvaluationDetails);
    }

    @Transactional
    @Override

    public void deleteSrvEvaluationDetails(long srvEvdId) {
        SrvEvaluationDetails srvEvaluationDetails = this.srvEvaluationDetailsRepository.findById(srvEvdId)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation Details Not Found"));

        srvEvaluationDetailsRepository.delete(srvEvaluationDetails);
    }
}
