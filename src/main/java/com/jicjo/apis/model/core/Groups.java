package com.jicjo.apis.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GROUPS", uniqueConstraints = {@UniqueConstraint(columnNames = {"GRB_ID"})})
public class Groups implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "GROUPS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "GRB_ID")
    private Long grbId;
    @Column(name = "GRB_TYPE")
    private Long grbType;
    @Column(name = "GRB_DESCRIPTION")
    private String grbDescription;
    @Column(name = "GRB_ACTIVE")
    private Boolean grbActive;
    @Column(name = "GRB_EN_GROUP_NAME")
    private String grbEnGroupName;
    @Column(name = "GRB_AR_GROUP_NAME")
    private String grbArGroupName;
    @Column(name = "GRB_EN_GROUP_DESC")
    private String grbEnGroupDesc;
    @Column(name = "GRB_AR_GROUP_DESC")
    private String grbArGroupDesc;
    @Column(name = "GRB_TASKS_DISTRIBUTION")
    private String grbTasksDistribution;
    @Column(name = "GRB_WORKING_DAYS")
    private Integer grbWorkingDays;
    @Column(name = "GRB_CREATED_BY")
    private String grbCreatedBy;
    @Column(name = "GRB_CREATED_ON")
    private Date grbCreatedOn;
    @Column(name = "GRB_UPDATED_BY")
    private String grbUpdatedBy;
    @Column(name = "GRB_UPDATED_ON")
    private Date grbUpdatedOn;

    @OneToMany(mappedBy = "groups",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<GroupMembers> groupMembersList = new ArrayList<>();

    @OneToMany(mappedBy = "groups",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<GroupScreens> groupScreensList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="GRB_CREATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="GRB_UPDATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users updatedBy;
}
