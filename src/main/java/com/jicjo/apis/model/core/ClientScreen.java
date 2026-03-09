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
@Table(name = "CLIENTS_SCREENS", uniqueConstraints = {@UniqueConstraint(columnNames = {"CSR_ID"})})
public class ClientScreen implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "CLIENTS_SCREENS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "CSR_ID")
    private Long csrId;
    @Column(name = "CLNT_NAME")
    private String clntName;
    @Column(name = "SCR_ID")
    private Long scrId;
    @Column(name = "CSR_CREATED_BY")
    private String csrCreatedBy;
    @Column(name = "CSR_CREATED_ON")
    private Date csrCreatedOn;
    @Column(name = "CSR_UPDATED_BY")
    private String csrUpdatedBy;
    @Column(name = "CSR_UPDATED_ON")
    private Date csrUpdatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="CLNT_NAME",referencedColumnName="CLNT_NAME",nullable=false,insertable = false, updatable = false)
    private Clients clients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="SCR_ID",referencedColumnName="SCR_ID",nullable=false,insertable = false, updatable = false)
    private ApplicationScreens applicationScreens;

    @OneToMany(mappedBy = "clientScreen",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<GroupScreens> groupScreensList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="CSR_CREATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="CSR_UPDATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users updatedBy;
}
