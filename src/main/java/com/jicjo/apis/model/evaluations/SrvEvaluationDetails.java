package com.jicjo.apis.model.evaluations;


import jakarta.persistence.*;
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
@Entity
@Table(name = "SRV_EVALUATION_DETAILS", uniqueConstraints = {@UniqueConstraint(columnNames = {"SRV_EVD_ID"})})
public class SrvEvaluationDetails  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "SRV_EVALUATION_DETAILS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "SRV_EVD_ID")
    private Long srvEvdId;
    @Column(name = "SRV_EVL_ID")
    private Long srvEvlId;
    @Column(name = "SRV_EVD_QUESTION_CODE")
    private String srvEvdQuestionCode;
    @Column(name = "SRV_EVD_QUESTION_AR")
    private String srvEvdQuestionAr;
    @Column(name = "SRV_EVD_QUESTION_EN")
    private String srvEvdQuestionEn;
    @Column(name = "SRV_EVD_ANSWER_TYPE")
    private Long srvEvdAnswerType;
    @Column(name = "SRV_EVD_RATE_VALUE")
    private Integer srvEvdRateValue;
    @Column(name = "SRV_EVD_TEXT_VALUE")
    private String srvEvdTextValue;
    @Column(name = "SRV_EVD_BOOL_VALUE")
    private String srvEvdBoolValue;
    @Column(name = "SRV_EVD_SEQ_NO")
    private Long srvEvdSeqNo;
    @Column(name = "SRV_EVD_CREATED_BY")
    private String srvEvdCreatedBy;
    @Column(name = "SRV_EVD_CREATED_DATE")
    private Date srvEvdCreatedDate;

}
