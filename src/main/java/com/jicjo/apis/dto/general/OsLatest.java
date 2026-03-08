package com.jicjo.apis.dto.general;


import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OsLatest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String BRANCH;
    private String BUSINESS_CLASS;
    private String BUSINESS_CODE;
    private String TERRITORY;
    private String PRODUCT_NAME;
    private String PRODUCT_CODE;
    private String POLICY_TYPE;
    private String POLICY_NUMBER;
    private String TYPE_OF_COVER;
    private String INSURED_NUMBER;
    private String INSURED_NAME;
    private String EFFECTIVE_DATE;
    private String EXPIRY_DATE;
    private String CITY;
    private String GENDER;
    private String AGE;
    private String CLAIM_NUMBER;
    private String DAMAGE_TYPE;
    private String CAR_PLATE;
    private String MT_CHASSIS_NO;
    private String COLOR_CAR;
    private String CAR_CATEGORY;
    private String CAR_VALUE;
    private String MT_PROD_YEAR;
    private String CAR_BRAND;
    private String CAR_SERIE;
    private String CAR_TYPE;
    private String REGISTRATION_DATE_DD;
    private String REGISTRATION_DATE_MM;
    private String REGISTRATION_DATE_YYYY;
    private String DATE_OF_LOSS_DD;
    private String DATE_OF_LOSS_MM;
    private String DATE_OF_LOSS_YYYY;
    private String DATE_OF_REPORTING_DD;
    private String DATE_OF_REPORTING_MM;
    private String DATE_OF_REPORTING_YYYY;
    private String GROSS_CLM_OS_RESERVE;
    private String OD_MATERIAL_OS;
    private String OD_BODLY_OS;
    private String OD_DEATH_OS;
    private String TP_MATERIAL_OS;
    private String TP_BODLY_OS;
    private String TP_BODLY_OS_1;
    private String OS_7;
    private String OS_8;
    private String OS_9;
    private String OS_10;
    private String AMBULANCE_OS;
    private String OUTSTANDING_NON_RECOVERY;
    private String REINSURANCE_SHARE;
    private String GCL_OS_REINAURNCE_LOCAL_FAC;
    private String GCL_OS_REINAURNCE_FRN_FAC;
    private String TRANSACTION_TYPE;
    private String REINSURANCE_TYPE;
    private String PAID;
    private String COM;
    private String TP;
    private String NATURE_OF_LOSS;
    private String PLC_UW_YEAR;
    private String COURT_CASE;
    private String RESPONSIPILITY_TYPE;
}
