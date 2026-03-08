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
public class PolicyRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("auth")
    private AuthRequestDto auth;
    @JsonProperty("insuranceCompanyCode")
    private String insuranceCompanyCode;
    @JsonProperty("policyNumber")
    private String policyNumber;
    @JsonProperty("policyIssueDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date policyIssueDate;
    @JsonProperty("policyStartDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date policyStartDate;
    @JsonProperty("policyExpiryDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date policyExpiryDate;
    @JsonProperty("policyType")
    private Long policyType;
    @JsonProperty("policyOwnerType")
    private Long policyOwnerType;
    @JsonProperty("policyOwnerName")
    private String policyOwnerName;
    @JsonProperty("policyOwnerID")
    private String policyOwnerID;
    @JsonProperty("planName")
    private String planName;
    @JsonProperty("operationType")
    private String operationType;
}
