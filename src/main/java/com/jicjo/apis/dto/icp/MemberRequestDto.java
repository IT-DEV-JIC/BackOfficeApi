package com.jicjo.apis.dto.icp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class MemberRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("auth")
    private AuthRequestDto auth;
    @JsonProperty("insuranceCompanyCode")
    private Long insuranceCompanyCode;
    @JsonProperty("policyCreationRefNo")
    private String policyCreationRefNo;
    @JsonProperty("policyNumber")
    private String policyNumber;
    @JsonProperty("membersList")
    private List<MemberListRequestDto> membersList;
}
