package com.jicjo.apis.dto.general;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductionDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String ID;
    private String BRANCH;
    private String BUSINESS_CLASS;
    private String BUSINESS_CODE;
    private String PRODUCT_CODE;
    private String INSURED_NUMBER;
    private String INSURED;
    private String ACCOUNT_NUMBER;
    private String ACCOUNT_NAME;
    private String BENEFICIARY_NUMBER;
    private String BENIFICARY_NAME;
    private String ACCOUNT_TYPE;
    private String POLICY_NUMBER;
    private String ENDORSEMENT_NUMBER;
    private String ENDORSEMENT_DESCRIPTION;
    private String TERRITORY;
    private String POLICY_TYPE;
    private String DIRECT_CHANNELS;
    private String BROKER;
    private String GENDER;
    private String AGE;
    private String MT_ENGINE_NO2;
    private String CAR_PLATE;
    private String MT_CHASSIS_NO;
    private String CAR_COLOR;
    private String MT_ENGINE_SIZE;
    private String CAR_CATEGORY;
    private String CAR_VALUE;
    private String MAKE_YEAR;
    private String CAR_BRAND;
    private String CAR_SERIE;
    private String CAR_TYPE;
    private String CYLINDER;
    private String NO_OF_SEATS;
    private String HORSEPOWER;
    private String USAGE;
    private String REPAIR_CONDITION;
    private String BODY_TYPE;
    private String DATE_ISS_DAY;
    private String DATE_ISS_MONTH;
    private String DATE_ISS_YEAR;
    private String ORIGINAL_EFF_DAY;
    private String ORIGINAL_EFF_MONTH;
    private String ORIGINAL_EFF_YEAR;
    private String DATE_EFF_DAY;
    private String DATE_EFF_MONTH;
    private String DATE_EFF_YEAR;
    private String DATE_EXP_DAY;
    private String DATE_EXP_MONTH;
    private String DATE_EXP_YEAR;
    private String DEDUCTIBLE;
    private String COINSURANCE;
    private String SUM_INSURED;
    private String GROSS_PREMIUM;
    private String DISCOUNT_APPLIED;
    private String PREM_AFTER_DIOSCOUNT;
    private String GROSS_TPL_PREM;
    private String GROSS_COMP_PREM;
    private String OTHER_PREMIUM;
    private String POL_ISS_FEES;
    private String INSURED_NATIONALITY;
    private String INSURED_NATIONALITY2;
    private String RED_CITY;
    private String CUSTOMER_TYPES;
    private String COMMISSION_PAID;
    private String REINSURANCE_TYPE;
    private String CLAIM_NO;
    private String BUSINESS_TYPES;
    private String REINSURANCE_COMMISSION;
    private String CLAIM_NO_1;
    private String REGISTRATION_CITY;
    private String ADDRESS;
    private String REINSURANCE_SHARE;
    private String REINSURANCE_SHARE_FAC;
    private String REINSURANCE_SHARE_LOCAL;
    private String CESSION_TYPE;
    private String FAC_REINSURANCE_COMMISSION;
    private String TOTAL_REINSURANCE_COMMISSION;
    private String CBJ_FEES;
    private String SALES_CBJ_FEES;
    private String FUEL_TYPE;
    private String INWARD_COMM;
    private String DIRECT_COMM;
    private String COMM_TYPE;
    private String CBJ4_5;
    private String CBJ_0_1;
    private String CREATED_BY;
}
