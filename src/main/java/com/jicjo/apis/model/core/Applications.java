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
@Table(name = "APPLICATIONS", uniqueConstraints = {@UniqueConstraint(columnNames = {"APP_ID"})})
public class Applications implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "APPLICATIONS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "APP_ID")
    private Long appId;
    @Column(name = "APP_NAME")
    private String appName;
    @Column(name = "APP_NAME_AR")
    private String appNameAr;
    @Column(name = "APP_LOGO")
    private String appLogo;
    @Column(name = "APP_CREATED_BY")
    private String appCreatedBy;
    @Column(name = "APP_CREATED_ON")
    private Date appCreatedOn;
    @Column(name = "APP_UPDATED_BY")
    private String appUpdatedBy;
    @Column(name = "APP_UPDATED_ON")
    private Date appUpdatedOn;

    public Applications(Long appId, String appName, String appNameAr, String appLogo, String appCreatedBy, Date appCreatedOn, String appUpdatedBy, Date appUpdatedOn) {
        this.appId = appId;
        this.appName = appName;
        this.appNameAr = appNameAr;
        this.appLogo = appLogo;
        this.appCreatedBy = appCreatedBy;
        this.appCreatedOn = appCreatedOn;
        this.appUpdatedBy = appUpdatedBy;
        this.appUpdatedOn = appUpdatedOn;
    }

    @OneToMany(mappedBy = "applications",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<ApplicationScreens> applicationScreensList = new ArrayList<>();

    @OneToMany(mappedBy = "applications",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<GroupScreens> groupScreensList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="APP_CREATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="APP_UPDATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users updatedBy;
}
