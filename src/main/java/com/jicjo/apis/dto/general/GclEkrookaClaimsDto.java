package com.jicjo.apis.dto.general;

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
public class GclEkrookaClaimsDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long Id;
    private String KrokaNo;
    private String AccidentId;
    private Date AccidentDate;
    private String TrafficSergentNo;
    private String TrafficSergentName;
    private Long JordanGovernorateId;
    private String GovernorateDesc;
    private Long VehicleCount;
    private Long AccidentPrimaryType;
    private String AccidentPrimaryTypeDesc;
    private Long AccidentSubType;
    private String AccidentSubTypeDesc;
    private Long AccidentOrangeSerialNo;
    private String Longitude;
    private String Latitude;
    private Long AccidentHurtCount;
    private Long IsActive;
    private Long SecurityCenterNo;
    private String SecurityCenterNoDesc;
    private Long Status;
    private String StatusDescription;
}
