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
@Table(name = "GROUP_SCREENS", uniqueConstraints = {@UniqueConstraint(columnNames = {"GSR_ID"})})
public class GroupScreens implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "GROUP_SCREENS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "GSR_ID")
    private Long gsrId;
    @Column(name = "APP_ID")
    private Long appId;
    @Column(name = "SCR_ID")
    private Long scrId;
    @Column(name = "CSR_ID")
    private Long csrId;
    @Column(name = "GSR_CLNT_NAME")
    private String gsrClntName;
    @Column(name = "GSR_CREATED_BY")
    private String gsrCreatedBy;
    @Column(name = "GSR_CREATED_ON")
    private Date gsrCreatedOn;
    @Column(name = "GSR_UPDATED_BY")
    private String gsrUpdatedBy;
    @Column(name = "GSR_UPDATED_ON")
    private Date gsrUpdatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="GRB_ID",referencedColumnName="GRB_ID",nullable=false,insertable = false, updatable = false)
    private Groups groups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="APP_ID",referencedColumnName="APP_ID",nullable=false,insertable = false, updatable = false)
    private Applications applications;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="SCR_ID",referencedColumnName="SCR_ID",nullable=false,insertable = false, updatable = false)
    private ApplicationScreens applicationScreens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="GSR_CLNT_NAME",referencedColumnName="CLNT_NAME",nullable=false,insertable = false, updatable = false)
    private Clients clients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="CSR_ID",referencedColumnName="CSR_ID",nullable=false,insertable = false, updatable = false)
    private ClientScreen clientScreen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="GSR_CREATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="GSR_UPDATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users updatedBy;
}
