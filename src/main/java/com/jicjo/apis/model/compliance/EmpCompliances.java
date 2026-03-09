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
@Table(name = "EMP_COMPLIANCES", uniqueConstraints = {@UniqueConstraint(columnNames = {"EMP_CMP_ID"})})
public class EmpCompliances implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "EMP_COMPLIANCES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "EMP_CMP_ID")
    private Long empCompliancesId;
    @Column(name = "EMP_CMP_TITLE")
    private String empCompliancesTitle;
    @Column(name = "EMP_CMP_BODY")
    private String empCompliancesBody;
    @Column(name = "EMP_CMP_IP")
    private String empCompliancesIp;
    @Column(name = "EMP_CMP_HOST")
    private String empCompliancesHost;
    @Column(name = "EMP_CMP_WINDOWS_USER")
    private String empCompliancesWindowsUser;
    @Column(name = "EMP_CMP_REQUEST_URI")
    private String empCompliancesRequestUri;
    @Column(name = "EMP_CMP_COMPLIANCED_ON")
    private Date empCompliancesCompliancesOn;
}
