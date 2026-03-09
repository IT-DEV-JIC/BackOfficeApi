package com.jicjo.apis.dto.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MotorComOsRecoveryLastRec  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("notificationDate")
    private String NOTIFICATION_DATE;
    @JsonProperty("gstPltCode")
    private String GST_PLT_CODE;
    @JsonProperty("gstClsId")
    private String GST_CLS_ID;
    @JsonProperty("crgBrnId")
    private String CRG_BRN_ID;
    @JsonProperty("crgCurCode")
    private String CRG_CUR_CODE;
    @JsonProperty("branch")
    private String BRANCH;
    @JsonProperty("insuranceClass")
    private String INSURANCE_CLASS;
    @JsonProperty("policyType")
    private String POLICY_TYPE;
    @JsonProperty("currency")
    private String CURRENCY;
    @JsonProperty("registrationDate")
    private String REGISTRATION_DATE;
    @JsonProperty("beneficiaryNumber")
    private String BENEFICIARY_NUMBER;
    @JsonProperty("beneficiaryName")
    private String BENEFICIARY_NAME;
    @JsonProperty("causeOfLoss")
    private String CAUSE_OF_LOSS;
    @JsonProperty("damageType")
    private String DAMAGE_TYPE;
    @JsonProperty("plateNo")
    private String PLATE_NO;
    @JsonProperty("mtChassisNo")
    private String MT_CHASSIS_NO;
    @JsonProperty("insuredNumber")
    private String INSURED_NUMBER;
    @JsonProperty("insuredName")
    private String INSURED_NAME;
    @JsonProperty("policyNo")
    private String POLICY_NO;
    @JsonProperty("claimNo")
    private String CLAIM_NO;
    @JsonProperty("lossDate")
    private String LOSS_DATE;
    @JsonProperty("lossDate")
    private String PAYMENT_AMOUNT;
    @JsonProperty("osAmount")
    private String OS_AMOUNT;
    @JsonProperty("responsipilityType")
    private String RESPONSIPILITY_TYPE;
    @JsonProperty("shareOs")
    private String SHARE_OS;
    @JsonProperty("transDateReserve")
    private String TRANS_DATE_RESERVE;
    @JsonProperty("courtCase")
    private String COURT_CASE;
    @JsonProperty("insuranceCo")
    private String INSURANCE_CO;
    @JsonProperty("insuranceCoClaimsPage")
    private String INSURANCE_CO_CLAIMS_PAGE;
}
