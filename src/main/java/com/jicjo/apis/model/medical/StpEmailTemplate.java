package com.jicjo.apis.model.medical;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STP_EMAIL_TEMPLATE", uniqueConstraints = {@UniqueConstraint(columnNames = {"STP_EMT_ID"})})
public class StpEmailTemplate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "STP_EMAIL_TEMPLATE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "STP_EMT_ID")
    private Long stpEmtId;
    @Column(name = "STP_EMT_CONNECT_BY")
    private Long stpEmtConnect;
    @NotNull
    @Column(name = "STP_EMT_TO")
    private String stpEmtTo;
    @Column(name = "STP_EMT_CC")
    private String stpEmtCc;
    @Column(name = "STP_EMT_BCC")
    private String stpEmtBcc;
    @NotNull
    @Column(name = "STP_EMT_TITLE")
    private String stpEmtTitle;
    @NotNull
    @Column(name = "STP_EMT_CONTENTS")
    private String stpEmtContents;
    @NotNull
    @Column(name = "STP_EMT_CLNT_NAME")
    private String stpEmtClntName;
    @NotNull
    @Column(name = "STP_EMT_CREATED_BY")
    private String stpEmtCreatedBy;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "STP_EMT_CREATED_ON")
    private String stpEmtCreatedOn;
    @Column(name = "STP_EMT_UPDATED_BY")
    private String stpEmtUpdatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "STP_EMT_UPDATED_ON")
    private String stpEmtUpdatedOn;
}
