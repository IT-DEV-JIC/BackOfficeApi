package com.jicjo.apis.dto.general;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepairPurchaseOrderDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String CLAIM_NUMBER;
    private String CLAIM_STATUS;
    private String CLAIMANT;
    private String CLAIMANT_CREATED_BY;
    private String CLAIMANT_CREATION_DATE;
    private String CLAIMANT_MODIFIED_BY;
    private String CLAIMANT_MODIFICATION_DATE;
    private String ORDER_NUMBER;
    private String REQUEST_CREATED_BY;
    private String PURCHASE_ORDER_SUPPLIER_TYPE;
    private String PURCHASE_ORDER_SUPPLIER_NAME;
    private String PURCHASE_ORDER_DESCRIPTION;
    private String PURCHASE_ORDER_AMOUNT;
    private String PURCHASE_ORDER_DATE;
    private String PURCHASE_STATUS_DATE;
    private String PURCHASE_CREATED_BY;
    private String PURCHASE_CREATION_DATE;
    private String PURCHASE_MODIFIED_BY;
    private String PURCHASE_MODIFICATION_DATE;
    private String PURCHASE_PAYMENT_TRANS_AMT;
    private String PURCHASE_PAYMENT_FEE_AMOUNT;
    private String PURCHASE_PAYMENT_NET_TRANS_AMT;
    private String REPAIR_ORDER_SUPPLIER_TYPE;
    private String REPAIR_ORDER_SUPPLIER_NAME;
    private String REPAIR_ORDER_DESCRIPTION;
    private String REPAIR_ORDER_AMOUNT;
    private String REPAIR_ORDER_DATE;
    private String REPAIR_PAYMENT_TRANS_AMT;
    private String REPAIR_PAYMENT_FEE_AMOUNT;
    private String REPAIR_PAYMENT_NET_TRANS_AMT;
    private String REPAIR_STATUS_DATE;
    private String REPAIR_CREATED_BY;
    private String REPAIR_CREATION_DATE;
    private String REPAIR_MODIFIED_BY;
    private String REPAIR_MODIFICATION_DATE;
    private String PURCHASE_ORDER_PARTS;
    private String MT_VEHICLE_TYPE;
    private String MT_VEHICLE_MODEL;
    private String MT_COLOR;
    private String MT_CATEGORY;
    private String MT_VEHICLE_BODY;
    private String MT_PROD_YEAR;
    private String DAMEG_PART_1;
    private String DAMEG_PART_2;
    private String DAMEG_PART_3;
    private String DAMEG_PART_4;
    private String DAMEG_PART_5;
    private String DAMEG_PART_6;
    private String DAMEG_PART_7;
    private String DAMEG_PART_8;
    private String DAMEG_PART_9;
    private String DAMEG_PART_10;
    private String DAMEG_PART_11;
    private String DAMEG_PART_12;
    private String DAMEG_PART_13;
    private String DAMEG_PART_14;
    private String DAMEG_PART_15;
    private String DAMEG_PART_16;
    private String DAMEG_PART_17;
}
