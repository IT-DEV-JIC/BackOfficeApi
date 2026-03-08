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
public class MedicalPoliciesDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("iCRequestRefNo")
    private Long ICREQUESTREFNO;
    @JsonProperty("username")
    private String USERNAME;
    @JsonProperty("password")
    private String PASSWORD;
    @JsonProperty("insuranceCompanyCode")
    private String INSURANCECOMPANYCODE;
    @JsonProperty("policyNumber")
    private String POLICYNUMBER;
    @JsonProperty("policyIssueDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date POLICYISSUEDATE;
    @JsonProperty("policyStartDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date POLICYSTARTDATE;
    @JsonProperty("policyExpiryDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date POLICYEXPIRYDATE;
    @JsonProperty("policyType")
    private Long POLICYTYPE;
    @JsonProperty("policyOwnerType")
    private Long POLICYOWNERTYPE;
    @JsonProperty("policyOwnerName")
    private String POLICYOWNERNAME;
    @JsonProperty("policyOwnerID")
    private String POLICYOWNERID;
    @JsonProperty("planName")
    private String PLANNAME;
    @JsonProperty("operationType")
    private String OPERATIONTYPE;
    @JsonProperty("postingStatus")
    private String POSTINGSTATUS;
}
