package com.jicjo.apis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "SYS_CODES")
public class SysCodes implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "SYS_ID")
    private String sysId;

    @Column(name = "SC_TYPE")
    private Long scType;
    @Column(name = "SC_CODE")
    private Long scCode;
    @Column(name = "SC_PARENT_TYPE")
    private Long scParentType;
    @Column(name = "SC_PARENT_CODE")
    private Long scParentCode;
    @Column(name = "SC_ADESC")
    private String scAdesc;
    @Column(name = "SC_LDESC")
    private String scLdesc;
    @Column(name = "SC_NVALUE")
    private Double scNvalue;
    @Column(name = "SC_VVALUE")
    private String scVvalue;
    @Column(name = "SC_CREATED_BY")
    private String scCreatedBy;
    @Column(name = "SC_CREATED_ON")
    private Date scCreatedOn;
    @Column(name = "SC_UPDATED_BY")
    private String scUpdatedBy;
    @Column(name = "SC_UPDATED_ON")
    private Date scUpdatedOn;
    @Column(name = "SC_FDESC")
    private String scFdesc;
    @Column(name = "SC_SERVICE_FLAG")
    private Integer scServiceFlag;
    @Column(name = "SC_VVALUE2")
    private String scVvalue2;

    public SysCodes(String sysId, Long scType, Long scCode, Long scParentType, Long scParentCode, String scAdesc, String scLdesc, Double scNvalue, String scVvalue, String scCreatedBy, Date scCreatedOn, String scUpdatedBy, Date scUpdatedOn, String scFdesc, Integer scServiceFlag, String scVvalue2) {
        this.sysId = sysId;
        this.scType = scType;
        this.scCode = scCode;
        this.scParentType = scParentType;
        this.scParentCode = scParentCode;
        this.scAdesc = scAdesc;
        this.scLdesc = scLdesc;
        this.scNvalue = scNvalue;
        this.scVvalue = scVvalue;
        this.scCreatedBy = scCreatedBy;
        this.scCreatedOn = scCreatedOn;
        this.scUpdatedBy = scUpdatedBy;
        this.scUpdatedOn = scUpdatedOn;
        this.scFdesc = scFdesc;
        this.scServiceFlag = scServiceFlag;
        this.scVvalue2 = scVvalue2;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="SC_CREATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="SC_UPDATED_BY",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users updatedBy;
}
