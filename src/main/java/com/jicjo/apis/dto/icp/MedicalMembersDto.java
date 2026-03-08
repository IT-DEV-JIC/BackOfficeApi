package com.jicjo.apis.dto.icp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicalMembersDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("jicId")
    private Long JICID;
    @JsonProperty("iCRequestRefNo")
    private Long  V_ICREQUESTREFNO;
    @JsonProperty("username")
    private String USERNAME;
    @JsonProperty("password")
    private String PASSWORD;
    @JsonProperty("insuranceCompanyCode")
    private String INSURANCECOMPANYCODE;
    @JsonProperty("policyCreationRefNo")
    private String POLICYCREATIONREFNO;
    @JsonProperty("policyNumber")
    private String POLICYNUMBER;
    @JsonProperty("memberRefNo")
    private String MEMBERREFNO;
    @JsonProperty("enrolmentIssueDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date ENROLMENTISSUEDATE;
    @JsonProperty("enrolmentStartDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date ENROLMENTSTARTDATE;
    @JsonProperty("unifiedNo")
    private String UNIFIEDNO;
    @JsonProperty("emiratesIDNo")
    private String EMIRATESIDNO;
    @JsonProperty("visaFileNo")
    private String VISAFILENO;
    @JsonProperty("birthCertNo")
    private String BIRTHCERTNO;
    @JsonProperty("passportNo")
    private String PASSPORTNO;
    @JsonProperty("gender")
    private Long GENDER;
    @JsonProperty("nationalityCode")
    private String NATIONALITYCODE;
    @JsonProperty("dateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date DATEOFBIRTH;
    @JsonProperty("fullNameEn")
    private String FULLNAMEEN;
    @JsonProperty("firstNameEn")
    private String FIRSTNAMEEN;
    @JsonProperty("middleNameEn")
    private String MIDDLENAMEEN;
    @JsonProperty("lastNameEn")
    private String LASTNAMEEN;
    @JsonProperty("fullNameAr")
    private String FULLNAMEAR;
    @JsonProperty("firstNameAr")
    private String FIRSTNAMEAR;
    @JsonProperty("middleNameAr")
    private String MIDDLENAMEAR;
    @JsonProperty("lastNameAr")
    private String LASTNAMEAR;
    @JsonProperty("maritalStatus")
    private Long MARITALSTATUS;
    @JsonProperty("relationWithSponsor")
    private Long RELATIONWITHSPONSOR;
    @JsonProperty("memberType")
    private Long MEMBERTYPE;
    @JsonProperty("sponsorIDNo")
    private String SPONSORIDNO;
    @JsonProperty("SponsorIDType")
    private String SPONSORIDTYPE;
    @JsonProperty("membershipCardNo")
    private String MEMBERSHIPCARDNO;
    @JsonProperty("className")
    private String CLASSNAME;
    @JsonProperty("occupationDesc")
    private String OCCUPATIONDESC;
    @JsonProperty("emiratesOfVisaCode")
    private String EMIRATESOFVISACODE;
    @JsonProperty("emiratesOfLivingCode")
    private String EMIRATESOFLIVINGCODE;
    @JsonProperty("postingStatus")
    private String POSTINGSTATUS;
}
