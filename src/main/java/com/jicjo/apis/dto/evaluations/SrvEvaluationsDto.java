package com.jicjo.apis.dto.evaluations;

import jakarta.persistence.Column;
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
public class SrvEvaluationsDto implements Serializable {

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
    private Integer srvEvlServiceRate;
    private Integer srvEvlStaffRate;
    private Integer srvEvlOverallRate;
    private String srvEvlComment;
    private Long srvEvlStatus;
    private String srvEvlCreatedBy;
    private Date srvEvlCreatedDate;
}
