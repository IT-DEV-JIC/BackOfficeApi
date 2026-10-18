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
@Table(name = "AI_SETUP", uniqueConstraints = {@UniqueConstraint(columnNames = {"AI_SETUP_ID"})})
public class AiSetup implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "SEQ_AI_SETUP", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "AI_SETUP_ID")
    private Long aiSetupId;

    @Column(name = "APP_ID")
    private Long appId;

    @Column(name = "AI_CODE")
    private String aiCode;

    @Column(name = "AI_NAME")
    private String aiName;

    @Column(name = "AI_NAME_AR")
    private String aiNameAr;

    @Column(name = "MODEL_NAME")
    private String modelName;

    @Column(name = "API_URL")
    private String apiUrl;
    @Lob
    @Column(name = "SYSTEM_PROMPT")
    private String systemPrompt;

    @Column(name = "TEMPERATURE")
    private Double temperature;

    @Column(name = "CONTEXT_SIZE")
    private Integer contextSize;

    @Column(name = "KEEP_ALIVE")
    private String keepAlive;

    @Column(name = "THINK_ENABLED")
    private Integer thinkEnabled;

    @Column(name = "ACTIVE_FLAG")
    private Integer activeFlag;

    @Column(name = "AI_CREATED_BY")
    private String aiCreatedBy;

    @Column(name = "AI_CREATED_ON")
    private Date aiCreatedOn;

    @Column(name = "AI_UPDATED_BY")
    private String aiUpdatedBy;

    @Column(name = "AI_UPDATED_ON")
    private Date aiUpdatedOn;
}
