package com.jicjo.apis.model.evaluations;


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
@Table(name = "SRV_EVALUATIONS", uniqueConstraints = {@UniqueConstraint(columnNames = {"SRV_EVL_ID"})})
public class SrvEvaluations implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "SRV_EVALUATIONS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "SRV_EVL_ID")
    private Long srvEvlId;
    @Column(name = "SRV_EVL_NO")
    private String srvEvlNo;
    @Column(name = "SRV_EVL_SOURCE_SYSTEM")
    private Long srvEvlSourceSystem;
    @Column(name = "SRV_EVL_REFERENCE_TYPE")
    private Long srvEvlReferenceType;
    @Column(name = "SRV_EVL_REFERENCE_NO")
    private String srvEvlReferenceNo;
    @Column(name = "SRV_EVL_SERVICE_TYPE")
    private Long srvEvlServiceType;
    @Column(name = "SRV_EVL_INSURANCE_TYPE")
    private Long srvEvlInsuranceType;
    @Column(name = "SRV_EVL_EMP_ID")
    private String srvEvlEmpId;
    @Column(name = "SRV_EVL_EMP_NAME")
    private String srvEvlEmpName;
    @Column(name = "SRV_EVL_SERVICE_RATE")
    private Integer srvEvlServiceRate;
    @Column(name = "SRV_EVL_STAFF_RATE")
    private Integer srvEvlStaffRate;
    @Column(name = "SRV_EVL_OVERALL_RATE")
    private Integer srvEvlOverallRate;
    @Column(name = "SRV_EVL_COMMENT")
    private String srvEvlComment;
    @Column(name = "SRV_EVL_STATUS")
    private Long srvEvlStatus;
    @Column(name = "SRV_EVL_CREATED_BY")
    private String srvEvlCreatedBy;
    @Column(name = "SRV_EVL_CREATED_DATE")
    private Date srvEvlCreatedDate;

}
