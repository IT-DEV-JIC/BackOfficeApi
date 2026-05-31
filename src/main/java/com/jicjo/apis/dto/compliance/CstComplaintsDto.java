package com.jicjo.apis.dto.compliance;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CstComplaintsDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long cstCmpId;
    private String cstCmpNumber;
    private Long cstCmpType;
    private String cstCmpFirstName;
    private String cstCmpFatherName;
    private String cstCmpGrandfatherName;
    private String cstCmpFamilyName;
    private String cstCmpPhoneNumber;
    private String cstCmpEmail;
    private String cstCmpTitle;
    private String cstCmpBody;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cstCmpCreationDate;
    private String cstCmpUpdateBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cstCmpUpdateDate;
    private String cstCdoUser;
    private Long cstCmpPriority;
    private Long cstCmpSource;
    private Long cstCmpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cstCmpStatusDate;
    private String cstCmpResponse;
    private String cstCmpResolution;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cstCmpAssignmentDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cstCmpStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cstCmpEndDate;
    private String cstCmpCreatedBy;
    private Long cstCmpInsuranceType;
    private String cstCmpLang;
}
