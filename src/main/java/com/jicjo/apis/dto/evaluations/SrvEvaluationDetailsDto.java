package com.jicjo.apis.dto.evaluations;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SrvEvaluationDetailsDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long srvEvdId;
    private Long srvEvlId;
    private String srvEvdQuestionCode;
    private String srvEvdQuestionAr;
    private String srvEvdQuestionEn;
    private Long srvEvdAnswerType;
    private Integer srvEvdRateValue;
    private String srvEvdTextValue;
}
