package com.jicjo.apis.model.compliance;

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
@Table(name = "CST_COMPLAINTS", uniqueConstraints = {@UniqueConstraint(columnNames = {"CST_CMP_ID"})})
public class CstComplaints implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "CST_COMPLAINTS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "CST_CMP_ID")
    private Long cstCmpId;
    @Column(name = "CST_CMP_NUMBER")
    private String cstCmpNumber;
    @Column(name = "CST_CMP_TYPE")
    private Long cstCmpType;
    @Column(name = "CST_CMP_FIRST_NAME")
    private String cstCmpFirstName;
    @Column(name = "CST_CMP_FATHER_NAME")
    private String cstCmpFatherName;
    @Column(name = "CST_CMP_GRANDFATHER_NAME")
    private String cstCmpGrandfatherName;
    @Column(name = "CST_CMP_FAMILY_NAME")
    private String cstCmpFamilyName;
    @Column(name = "CST_CMP_PHONE_NUMBER")
    private String cstCmpPhoneNumber;
    @Column(name = "CST_CMP_EMAIL")
    private String cstCmpEmail;
    @Column(name = "CST_CMP_TITLE")
    private String cstCmpTitle;
    @Column(name = "CST_CMP_BODY")
    private String cstCmpBody;
    @Column(name = "CST_CMP_CREATION_DATE")
    private Date cstCmpCreationDate;
    @Column(name = "CST_CMP_UPDATE_BY")
    private String cstCmpUpdateBy;
    @Column(name = "CST_CMP_UPDATE_DATE")
    private Date cstCmpUpdateDate;
    @Column(name = "CST_CDO_USER")
    private String cstCdoUser;
    @Column(name = "CST_CMP_PRIORITY")
    private Long cstCmpPriority;
    @Column(name = "CST_CMP_SOURCE")
    private Long cstCmpSource;
    @Column(name = "CST_CMP_STATUS")
    private Long cstCmpStatus;
    @Column(name = "CST_CMP_STATUS_DATE")
    private Date cstCmpStatusDate;
    @Column(name = "CST_CMP_RESPONSE")
    private String cstCmpResponse;
    @Column(name = "CST_CMP_RESOLUTION")
    private String cstCmpResolution;
    @Column(name = "CST_CMP_ASSIGNMENT_DATE")
    private Date cstCmpAssignmentDate;
    @Column(name = "CST_CMP_START_DATE")
    private Date cstCmpStartDate;
    @Column(name = "CST_CMP_END_DATE")
    private Date cstCmpEndDate;
    @Column(name = "CST_CMP_CREATED_BY")
    private String cstCmpCreatedBy;
    @Column(name ="CST_CMP_INSURANCE_TYPE")
    private Long cstCmpInsuranceType;
}
