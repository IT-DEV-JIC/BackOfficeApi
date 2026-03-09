package com.jicjo.apis.dto.icp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class MemberListRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("memberRefNo")
    private String memberRefNo;
    @JsonProperty("enrolmentIssueDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date enrolmentIssueDate;
    @JsonProperty("enrolmentStartDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date enrolmentStartDate;
    @JsonProperty("unifiedNo")
    private String unifiedNo;
    @JsonProperty("emiratesIDNo")
    private String emiratesIDNo;
    @JsonProperty("visaFileNo")
    private String visaFileNo;
    @JsonProperty("birthCertNo")
    private String birthCertNo;
    @JsonProperty("passportNo")
    private String passportNo;
    @JsonProperty("gender")
    private Long gender;
    @JsonProperty("nationalityCode")
    private String nationalityCode;
    @JsonProperty("dateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateOfBirth;
    @JsonProperty("fullNameEn")
    private String fullNameEn;
    @JsonProperty("firstNameEn")
    private String firstNameEn;
    @JsonProperty("middleNameEn")
    private String middleNameEn;
    @JsonProperty("lastNameEn")
    private String lastNameEn;
    @JsonProperty("fullNameAr")
    private String fullNameAr;
    @JsonProperty("firstNameAr")
    private String firstNameAr;
    @JsonProperty("middleNameAr")
    private String middleNameAr;
    @JsonProperty("lastNameAr")
    private String lastNameAr;
    @JsonProperty("maritalStatus")
    private Long maritalStatus;
    @JsonProperty("relationWithSponsor")
    private Long relationWithSponsor;
    @JsonProperty("memberType")
    private Long memberType;
    @JsonProperty("sponsorIDNo")
    private String sponsorIDNo;
    @JsonProperty("SponsorIDType")
    private String sponsorIDType;
    @JsonProperty("membershipCardNo")
    private String membershipCardNo;
    @JsonProperty("className")
    private String className;
    @JsonProperty("occupationDesc")
    private String occupationDesc;
    @JsonProperty("emiratesOfVisaCode")
    private String emiratesOfVisaCode;
    @JsonProperty("emiratesOfLivingCode")
    private Long emiratesOfLivingCode;

}
