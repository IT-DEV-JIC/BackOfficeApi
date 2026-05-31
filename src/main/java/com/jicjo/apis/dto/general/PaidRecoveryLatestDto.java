package com.jicjo.apis.dto.general;


import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaidRecoveryLatestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String BRANCH;
    private String BUSINESS_CLASS;
    private String BUSINESS_CODE;
    private String PRODUCT_NAME;
    private String PRODUCT_CODE;
    private String ACCOUNT_TYPE;
    private String TERRITORY;
    private String POLICY_NUMBER;
    private String EFFECTIVE_DATE;
    private String EXPIRY_DATE;
    private String INSURED_NUMBER;
    private String GENDER;
    private String AGE;
    private String CLAIM_NUMBER;
    private String DATE_OF_LOSS_DD;
    private String DATE_OF_LOSS_MM;
    private String DATE_OF_LOSS_YYYY;
    private String DATE_OF_REPORTING_DD;
    private String DATE_OF_REPORTING_MM;
    private String DATE_OF_REPORTING_YYYY;
    private String DATE_OF_TRANSACTION_DD;
    private String DATE_OF_TRANSACTION_MM;
    private String DATE_OF_TRANSACTION_YYYY;
    private String TRANSACTION_AMOUNT;
    private String TP_MATREIAL;
    private String TP_BODLY;
    private String TP_DEATH;
    private String OD_MATREIAL;
    private String OD_BODLY;
    private String OD_DEATH;
    private String OTHERS;
    private String SUM_PAYM_TRANS_DET;
    private String INSURANCE_COMPANY;
    private String INDIVIDUAL;
    private String SELLING_SCRAP;
    private String DEDUCTIBLE;
    private String DEPRECIATION;
    private String SHIP_OWNERS;
    private String P_I_CLUB;
    private String HAULER;
    private String GENERAL_AVERAGE;
    private String RECOVERY_FROM_INSURED;
    private String DED_DEPRETIATION;
    private String DEBITOR_DED_DEPRETIATION;
    private String SUM_REC;
    private String REINSURANCE_SHARE;
    private String REINSURANCE_LOCAL_SHARE;
    private String REINSURANCE_FRN_SHARE;
    private String TRANSACTION_TYPE;
    private String REINSURANCE_TYPE;
    private String COURT_CASE;
    private String CAR_PLATE;
    private String MT_CHASSIS_NO;
    private String CAR_PLATE_FROM_CLAIM;
    private String MT_CHASSIS_NO_FROM_CLAIM;
    private String COLOR_CAR;
    private String CAR_CATEGORY;
    private String CAR_VALUE;
    private String MAKE_YEAR;
    private String CAR_BRAND;
    private String CAR_SERIE;
    private String CAR_TYPE;
    private String PLC_UW_YEAR;
    private String NATURE_OF_LOSS;
    private String EFFECTIVEDATE;
    private String EXPIRYDATE;
    private String CITY;
    private String INSURED_NATIONALITY;
    private String INSURED_NATIONALITY2;
    private String RED_CITY;
    private String CLAIM_STATUS;
    private String CST_NAME;
    private String PURCHASE_ORDER;
    private String REPAIR_ORDER;
    private String RESPONSIPILITY_TYPE;

}
