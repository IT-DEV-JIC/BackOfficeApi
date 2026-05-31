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
@Table(name = "CST_CMP_RATE", uniqueConstraints = {@UniqueConstraint(columnNames = {"CST_CMR_ID"})})
public class CstCmpRate implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "CST_CMP_RATE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "CST_CMR_ID")
    private Long cstCmrId;
    @Column(name = "CST_CMP_ID")
    private Long cstCmpId;
    @Column(name = "CST_CDO_USER")
    private String cstCdoUser;
    @Column(name = "CST_CMR_STARS")
    private Long cstCmrStars;
    @Column(name = "CST_CMR_CREATION_DATE")
    private Date cstCmrCreationDate;

}
