package com.jicjo.apis.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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
@Table(name = "APPLICATION_SCREENS", uniqueConstraints = {@UniqueConstraint(columnNames = {"SCR_ID"})})
public class ApplicationScreens implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "APPLICATION_SCREENS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "SCR_ID")
    private Long scrId;
    @NotNull
    @Column(name = "APP_ID")
    private Long appId;
    @Column(name = "SCR_NAME")
    private String scrName;
    @Column(name = "SCR_NAME_AR")
    private String scrNameAr;
    @Column(name = "SCR_CREATED_BY")
    private String scrCreatedBy;
    @Column(name = "SCR_CREATED_ON")
    private Date scrCreatedOn;
    @Column(name = "SCR_UPDATED_BY")
    private String scrUpdatedBy;
    @Column(name = "SCR_UPDATED_ON")
    private Date scrUpdatedOn;
    @Column(name = "SCR_URL")
    private String scrUrl;

    public ApplicationScreens(Long scrId, Long appId, String scrName, String scrNameAr, String scrCreatedBy, Date scrCreatedOn, String scrUpdatedBy, Date scrUpdatedOn, String scrUrl) {
        this.scrId = scrId;
        this.appId = appId;
        this.scrName = scrName;
        this.scrNameAr = scrNameAr;
        this.scrCreatedBy = scrCreatedBy;
        this.scrCreatedOn = scrCreatedOn;
        this.scrUpdatedBy = scrUpdatedBy;
        this.scrUpdatedOn = scrUpdatedOn;
        this.scrUrl = scrUrl;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="APP_ID",referencedColumnName="APP_ID",nullable=false,insertable = false, updatable = false)
    private Applications applications;

    @OneToMany(mappedBy = "applicationScreens",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<ClientScreen> clientScreenList = new ArrayList<>();

    @OneToMany(mappedBy = "applicationScreens",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<GroupScreens> groupScreensList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="SCR_CREATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="SCR_UPDATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users updatedBy;
}
