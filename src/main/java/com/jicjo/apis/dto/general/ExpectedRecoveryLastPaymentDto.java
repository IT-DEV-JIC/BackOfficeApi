package com.jicjo.apis.dto.general;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpectedRecoveryLastPaymentDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String SEGMENT_CODE;
    private String TRANS_DATE_REC;
    private String OUR_POLICY_NO;
    private String REC_TYPE;
    private String INSURANCE_CO;
    private String INSURED_NUMBER;
    private String INSURED;
    private String PLATE_NO;
    private String MT_CHASSIS_NO;
    private String TP_PLATE_NO;
    private String POLICY_NO;
    private String  RECOVERY;
    private String DAMAGE_TYPE;
    private String AS_OF_DATE;
    private String TRANS_DATE_PAYMENT;
    private String LOSS_DATE;
    private String CURRUNCY;
    private String CLASS_NAME;
    private String POLICY_TYPE;
    private String BRANCH;
    private String COURT_CASE;
    private String RESPONSIPILITY_TYPE;
}
