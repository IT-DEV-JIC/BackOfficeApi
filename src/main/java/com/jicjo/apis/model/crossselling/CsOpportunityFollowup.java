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
@Table(name = "CS_OPPORTUNITY_FOLLOWUPS", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class CsOpportunityFollowup implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "CS_OPPORTUNITY_FOLLOWUPS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "ID")
    private Long id;

    @Column(name = "OPPORTUNITY_ID")
    private Long opportunityId;

    @Column(name = "ACTION_TYPE")
    private String actionType;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "FOLLOWUP_DATE")
    private Date followupDate;

    @Column(name = "NEXT_FOLLOWUP_DATE")
    private Date nextFollowupDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;
}
