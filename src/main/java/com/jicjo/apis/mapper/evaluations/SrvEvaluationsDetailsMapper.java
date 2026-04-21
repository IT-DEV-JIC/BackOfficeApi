package com.jicjo.apis.mapper.evaluations;

import com.jicjo.apis.dto.evaluations.SrvEvaluationDetailsDto;
import com.jicjo.apis.dto.evaluations.SrvEvaluationDto;
import com.jicjo.apis.model.evaluations.SrvEvaluationDetails;
import com.jicjo.apis.model.evaluations.SrvEvaluations;

import java.io.Serial;
import java.util.List;
import java.util.stream.Collectors;

public class SrvEvaluationsDetailsMapper {
    @Serial
    private static final long serialVersionUID = 1L;

    public static SrvEvaluationDetailsDto srvEvaluationDetailsDto (SrvEvaluationDetails srvEvaluationDetails) {
        return new SrvEvaluationDetailsDto(
                srvEvaluationDetails.getSrvEvdId(),
                srvEvaluationDetails.getSrvEvlId(),
                srvEvaluationDetails.getSrvEvdQuestionCode(),
                srvEvaluationDetails.getSrvEvdQuestionAr(),
                srvEvaluationDetails.getSrvEvdQuestionEn(),
                srvEvaluationDetails.getSrvEvdAnswerType(),
                srvEvaluationDetails.getSrvEvdRateValue(),
                srvEvaluationDetails.getSrvEvdTextValue(),
                srvEvaluationDetails.getSrvEvdBoolValue(),
                srvEvaluationDetails.getSrvEvdSeqNo(),
                srvEvaluationDetails.getSrvEvdCreatedBy(),
                srvEvaluationDetails.getSrvEvdCreatedDate()
        );
    }

    public static SrvEvaluationDetails srvEvaluationDetailsDto (SrvEvaluationDetailsDto srvEvaluationDetailsDto){
        return new SrvEvaluationDetails(
                srvEvaluationDetailsDto.getSrvEvdId(),
                srvEvaluationDetailsDto.getSrvEvlId(),
                srvEvaluationDetailsDto.getSrvEvdQuestionCode(),
                srvEvaluationDetailsDto.getSrvEvdQuestionAr(),
                srvEvaluationDetailsDto.getSrvEvdQuestionEn(),
                srvEvaluationDetailsDto.getSrvEvdAnswerType(),
                srvEvaluationDetailsDto.getSrvEvdRateValue(),
                srvEvaluationDetailsDto.getSrvEvdTextValue(),
                srvEvaluationDetailsDto.getSrvEvdBoolValue(),
                srvEvaluationDetailsDto.getSrvEvdSeqNo(),
                srvEvaluationDetailsDto.getSrvEvdCreatedBy(),
                srvEvaluationDetailsDto.getSrvEvdCreatedDate()
        );
    }

    public static List<SrvEvaluationDetailsDto> srvEvaluationDetailsDtoList(List<SrvEvaluationDetails> srvEvaluations) {
        return srvEvaluations.stream()
                .map(SrvEvaluationsDetailsMapper::srvEvaluationDetailsDto)
                .collect(Collectors.toList());
    }
}
