package com.jicjo.apis.model.crossselling;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CS_OPPORTUNITIES", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class CsOpportunity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "CS_OPPORTUNITIES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "ID")
    private Long id;

    @Column(name = "OPPORTUNITY_NO")
    private String opportunityNo;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "RULE_ID")
    private Long ruleId;

    @Column(name = "SOURCE_LOB")
    private String sourceLob;

    @Column(name = "TARGET_LOB")
    private String targetLob;

    @Column(name = "OPPORTUNITY_TYPE")
    private String opportunityType;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "ASSIGNED_TO")
    private String assignedTo;

    @Column(name = "EXPECTED_PREMIUM")
    private BigDecimal expectedPremium;

    @Column(name = "ACTUAL_PREMIUM")
    private BigDecimal actualPremium;

    @Column(name = "WON_POLICY_SOURCE")
    private String wonPolicySource;

    @Column(name = "WON_POLICY_ID")
    private Long wonPolicyId;

    @Column(name = "WON_POLICY_NO")
    private String wonPolicyNo;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "CLOSED_BY")
    private String closedBy;

    @Column(name = "CLOSED_DATE")
    private Date closedDate;
}
