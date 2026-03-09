package com.jicjo.apis.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENTS", uniqueConstraints = {@UniqueConstraint(columnNames = {"CLNT_NAME"})})
public class Clients implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CLNT_NAME")
    private String clntName;
    @Column(name = "CLNT_EN_CLIENT_NAME")
    private String clntEnClientName;
    @Column(name = "CLNT_AR_CLIENT_NAME")
    private String clntArClientName;
    @Column(name = "CLNT_CLIENT_MOBILE")
    private String clntClientMobile;
    @Column(name = "CLNT_CLIENT_EMAIL")
    private String clntClientEmail;
    @Column(name = "CLNT_EN_ADDRESS")
    private String clntEnAddress;
    @Column(name = "CLNT_AR_ADDRESS")
    private String clntArAddress;
    @Column(name = "CLNT_EN_BENEFICIARY_NAME")
    private String clntEnBeneficiaryName;
    @Column(name = "CLNT_AR_BENEFICIARY_NAME")
    private String clntArBeneficiaryName;
    @Column(name = "CLNT_BENEFICIARY_MOBILE")
    private String clntBeneficiaryMobile;
    @Column(name = "CLNT_BENEFICIARY_EMAIL")
    private String clntBeneficiaryEmail;
    @Column(name = "CLNT_ACTIVE")
    private Long clntActive;
    @Column(name = "CLNT_ACTIVE_FROM")
    private Date clntActiveFrom;
    @Column(name = "CLNT_ACTIVE_TO")
    private Date clntActiveTo;
    @Column(name = "CLNT_SUBSCRIPTIONـAMOUNT")
    private BigDecimal clntSubscriptionAmount;
    @Column(name = "CLNT_PAYMENT_STATUS")
    private Long clntPaymentStatus;
    @Column(name = "CLNT_CREATED_BY")
    private String clntCreatedBy;
    @Column(name = "CLNT_CREATED_ON")
    private Date clntCreatedOn;
    @Column(name = "CLNT_UPDATED_BY")
    private String clntUpdatedBy;
    @Column(name = "CLNT_UPDATED_ON")
    private Date clntUpdatedOn;
    @Column(name = "CLNT_TAX_NO")
    private String clntTaxNo;
    @Column(name = "CLNT_LOGO")
    private String clntLogo;
    @Column(name = "CST_IDS")
    private String cstIds;
    @Column(name = "CLNT_ATTACHMENT_TEMPLATE")
    private String clntAttachmentTemplate;

    public Clients(String clntName, String clntEnClientName, String clntArClientName, String clntClientMobile, String clntClientEmail, String clntEnAddress, String clntArAddress, String clntEnBeneficiaryName, String clntArBeneficiaryName, String clntBeneficiaryMobile, String clntBeneficiaryEmail, Long clntActive, Date clntActiveFrom, Date clntActiveTo, BigDecimal clntSubscriptionAmount, Long clntPaymentStatus, String clntCreatedBy, Date clntCreatedOn, String clntUpdatedBy, Date clntUpdatedOn, String clntTaxNo, String clntLogo, String cstIds, String clntAttachmentTemplate) {
        this.clntName = clntName;
        this.clntEnClientName = clntEnClientName;
        this.clntArClientName = clntArClientName;
        this.clntClientMobile = clntClientMobile;
        this.clntClientEmail = clntClientEmail;
        this.clntEnAddress = clntEnAddress;
        this.clntArAddress = clntArAddress;
        this.clntEnBeneficiaryName = clntEnBeneficiaryName;
        this.clntArBeneficiaryName = clntArBeneficiaryName;
        this.clntBeneficiaryMobile = clntBeneficiaryMobile;
        this.clntBeneficiaryEmail = clntBeneficiaryEmail;
        this.clntActive = clntActive;
        this.clntActiveFrom = clntActiveFrom;
        this.clntActiveTo = clntActiveTo;
        this.clntSubscriptionAmount = clntSubscriptionAmount;
        this.clntPaymentStatus = clntPaymentStatus;
        this.clntCreatedBy = clntCreatedBy;
        this.clntCreatedOn = clntCreatedOn;
        this.clntUpdatedBy = clntUpdatedBy;
        this.clntUpdatedOn = clntUpdatedOn;
        this.clntTaxNo = clntTaxNo;
        this.clntLogo = clntLogo;
        this.cstIds = cstIds;
        this.clntAttachmentTemplate = clntAttachmentTemplate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="CLNT_CREATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="CLNT_UPDATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users updatedBy;

    /* Client Name Foreign Key */
    @OneToMany(mappedBy = "clients",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<ClientScreen> clientScreenList = new ArrayList<>();

    @OneToMany(mappedBy = "clients",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<GroupScreens> groupScreensList = new ArrayList<>();

}
