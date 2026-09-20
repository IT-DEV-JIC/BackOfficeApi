package com.jicjo.apis.model.crossselling;


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
@Table(name = "CS_IGNORE_LIST", uniqueConstraints = {@UniqueConstraint(columnNames = {"IGNORE_ID"})})
public class CsIgnoreList  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "CS_IGNORE_LIST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "IGNORE_ID")
    private Long ignoreId;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "CUSTOMER_NO")
    private String customerNo;

    @Column(name = "SOURCE_LOB", nullable = false)
    private String sourceLob;

    @Column(name = "TARGET_LOB", nullable = false)
    private String targetLob;

    @Column(name = "RULE_ID")
    private Long ruleId;

    @Column(name = "IGNORE_REASON")
    private String ignoreReason;

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "IS_ACTIVE")
    private Integer isActive;
}
