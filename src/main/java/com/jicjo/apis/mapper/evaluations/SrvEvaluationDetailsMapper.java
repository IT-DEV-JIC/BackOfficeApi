package com.jicjo.apis.mapper.evaluations;

import com.jicjo.apis.dto.evaluations.SrvEvaluationDetailsDto;
import com.jicjo.apis.dto.evaluations.SrvEvaluationsDto;
import com.jicjo.apis.model.evaluations.SrvEvaluationDetails;
import com.jicjo.apis.model.evaluations.SrvEvaluations;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class SrvEvaluationDetailsMapper implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static SrvEvaluationDetailsDto toSrvEvaluationDetailsDto(SrvEvaluationDetails srvEvaluationDetails){
        return new SrvEvaluationDetailsDto(
                srvEvaluationDetails.getSrvEvdId(),
                srvEvaluationDetails.getSrvEvlId(),
                srvEvaluationDetails.getSrvEvdQuestionCode(),
                srvEvaluationDetails.getSrvEvdQuestionAr(),
                srvEvaluationDetails.getSrvEvdQuestionEn(),
                srvEvaluationDetails.getSrvEvdAnswerType(),
                srvEvaluationDetails.getSrvEvdRateValue(),
                srvEvaluationDetails.getSrvEvdTextValue()
        );
    }

    public static SrvEvaluationDetails toSrvEvaluationDetails(SrvEvaluationDetailsDto srvEvaluationDetailsDto){
        return new SrvEvaluationDetails(
                srvEvaluationDetailsDto.getSrvEvdId(),
                srvEvaluationDetailsDto.getSrvEvlId(),
                srvEvaluationDetailsDto.getSrvEvdQuestionCode(),
                srvEvaluationDetailsDto.getSrvEvdQuestionAr(),
                srvEvaluationDetailsDto.getSrvEvdQuestionEn(),
                srvEvaluationDetailsDto.getSrvEvdAnswerType(),
                srvEvaluationDetailsDto.getSrvEvdRateValue(),
                srvEvaluationDetailsDto.getSrvEvdTextValue()
        );
    }

    public static List<SrvEvaluationDetailsDto> toSrvEvaluationDetailsDtoList(List<SrvEvaluationDetails> srvEvaluationDetails) {
        return srvEvaluationDetails.stream()
                .map(SrvEvaluationDetailsMapper::toSrvEvaluationDetailsDto)
                .collect(Collectors.toList());
    }
}
