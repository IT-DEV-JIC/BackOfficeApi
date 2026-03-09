package com.jicjo.apis.model.general;


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
@Table(name = "GCL_EKROOKA_CLAIMS", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class GclEkrookaClaims implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "GCL_EKROOKA_CLAIMS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "ID")
    private Long Id;
    @Column(name = "KROKA_NO")
    private String KrokaNo;
    @Column(name = "ACCIDENT_ID")
    private String AccidentId;
    @Column(name = "ACCIDENT_DATE")
    private Date AccidentDate;
    @Column(name = "TRAFFIC_SERGENT_NO")
    private String TrafficSergentNo;
    @Column(name = "TRAFFIC_SERGENT_NAME")
    private String TrafficSergentName;
    @Column(name = "JORDAN_GOVERNORATE_ID")
    private Long JordanGovernorateId;
    @Column(name = "GOVERNORATE_DESC")
    private String GovernorateDesc;
    @Column(name = "VEHICLE_COUNT")
    private Long VehicleCount;
    @Column(name = "ACCIDENT_PRIMARY_TYPE")
    private Long AccidentPrimaryType;
    @Column(name = "ACCIDENT_PRIMARY_TYPE_DESC")
    private String AccidentPrimaryTypeDesc;
    @Column(name = "ACCIDENT_SUB_TYPE")
    private Long AccidentSubType;
    @Column(name = "ACCIDENT_SUB_TYPE_DESC")
    private String AccidentSubTypeDesc;
    @Column(name = "ACCIDENT_ORANGE_SERIAL_NO")
    private Long AccidentOrangeSerialNo;
    @Column(name = "LONGITUDE")
    private String Longitude;
    @Column(name = "LATITUDE")
    private String Latitude;
    @Column(name = "ACCIDENT_HURT_COUNT")
    private Long AccidentHurtCount;
    @Column(name = "IS_ACTIVE")
    private Long IsActive;
    @Column(name = "SECURITY_CENTER_NO")
    private Long SecurityCenterNo;
    @Column(name = "SECURITY_CENTER_NO_DESC")
    private String SecurityCenterNoDesc;
    @Column(name = "STATUS")
    private Long Status;
    @Column(name = "STATUS_DESCRIPTION")
    private String StatusDescription;
}
