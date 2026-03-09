package com.jicjo.apis.model.icp;


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
@Table(name = "JIC_ICP_MEMBERS", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class JicIcpMembers implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "JIC_ICP_MEMBERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "ID")
    private Long id;
    @Column(name = "JICID")
    private Long jicId;
    @Column(name = "JICMBRID")
    private Long jicMbrId;
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;
    @Column(name = "MEMBERREFNO")
    private String memberRefNo;
    @Column(name = "MEMBERCREATIONREFNO")
    private String memberCreationRefNo;
    @Column(name = "MEMBERSTATUSCODE")
    private Long memberStatusCode;
    @Column(name = "MEMBERERRORCODE")
    private String memberErrorCode;
    @Column(name = "MEMBERERRORDESC")
    private String memberErrorDesc;
    @Column(name = "STATUSCODE")
    private Long statusCode;
    @Column(name = "ERRORCODE")
    private String errorCode;
    @Column(name = "ERRORDESC")
    private String errorDesc;
    @Column(name = "ICP_JSON")
    private String icpJson;
    @Column(name = "JIC_JSON")
    private String jicJson;
    @Column(name = "ACTION")
    private String action;
    @Column(name = "JIC_CON_ID")
    private Long jicConId;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_ON")
    private Date createdOn;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_ON")
    private Date updatedOn;
}
