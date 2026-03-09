package com.jicjo.apis.model.core;

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
@Table(name = "GROUP_MEMBERS", uniqueConstraints = {@UniqueConstraint(columnNames = {"GRBMEM_ID"})})
public class GroupMembers implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "GROUP_MEMBERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "GRBMEM_ID")
    private Long grbMemId;
    @Column(name = "GRBMEM_MEMBER")
    private String grbMemMember;
    @Column(name = "GRBMEM_CREATED_BY")
    private String grbMemCreatedBy;
    @Column(name = "GRBMEM_CREATED_ON")
    private Date grbMemCreatedOn;
    @Column(name = "GRBMEM_UPDATED_BY")
    private String grbMemUpdatedBy;
    @Column(name = "GRBMEM_UPDATED_ON")
    private Date grbMemUpdatedOn;
    @Column(name = "GRB_ID")
    private Long grpId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="GRB_ID",referencedColumnName="GRB_ID",nullable=false,insertable = false, updatable = false)
    private Groups groups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="GRBMEM_CREATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="GRBMEM_UPDATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users updatedBy;
}
