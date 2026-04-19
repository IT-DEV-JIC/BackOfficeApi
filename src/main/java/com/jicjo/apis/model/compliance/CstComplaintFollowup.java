package com.jicjo.apis.model.compliance;


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
@Table(name = "CST_COMPLAINT_FOLLOWUP", uniqueConstraints = {@UniqueConstraint(columnNames = {"CST_CFL_ID"})})
public class CstComplaintFollowup implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "CST_COMPLAINT_FOLLOWUP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "CST_CFL_ID")
    private Long cstCflId;
    @Column(name = "CST_CMP_ID")
    private Long cstCmpId;
    @Column(name = "CST_CFL_NOTE")
    private String cstCflNote;
    @Column(name = "CST_CFL_STATUS_BEFORE")
    private Long cstCflStatusBefore;
    @Column(name = "CST_CFL_STATUS_AFTER")
    private Long cstCflStatusAfter;
    @Column(name = "CST_CFL_ACTION_TYPE")
    private Long cstCflActionType;
    @Column(name = "CST_CFL_ASSIGNED_TO")
    private String cstCflAssignedTo;
    @Column(name = "CST_CFL_CREATED_BY")
    private String cstCflCreatedBy;
    @Column(name = "CST_CFL_CREATED_DATE")
    private Date cstCflCreatedDate;
    @Column(name = "CST_CFL_REQUEST_ATTACHMENT")
    private String cstCflRequestAttachment;
    @Column(name = "CST_CFL_RESPONCER_ATTACHMENT")
    private String cstCflResponcerAttachment;
}
