package com.jicjo.apis.dto.evaluations;

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
public class SrvEvaluationDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long srvEvlId;
    private String srvEvlNo;
    private Long srvEvlSourceSystem;
    private Long srvEvlReferenceType;
    private String srvEvlReferenceNo;
    private Long srvEvlServiceType;
    private Long srvEvlInsuranceType;
    private String srvEvlEmpId;
    private String srvEvlEmpName;
    private Long srvEvlDeptId;
    private String srvEvlDeptName;
    private Long srvEvlBranchId;
    private String srvEvlBranchName;
    private String srvEvlCustomerName;
    private String srvEvlPhoneNo;
    private String srvEvlEmail;
    private Integer srvEvlServiceRate;
    private Integer srvEvlStaffRate;
    private Integer srvEvlOverallRate;
    private String srvEvlRecommendFlag;
    private String srvEvlComment;
    private String srvEvlInternalNote;
    private Long srvEvlStatus;
    private Date srvEvlStatusDate;
    private String srvEvlCreatedBy;
    private Date srvEvlCreatedDate;
    private String srvEvlUpdatedBy;
    private Date srvEvlUpdatedDate;

}
