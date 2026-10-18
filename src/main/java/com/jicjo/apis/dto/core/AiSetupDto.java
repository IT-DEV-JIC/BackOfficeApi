package com.jicjo.apis.dto.core;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
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
public class AiSetupDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long aiSetupId;
    private Long appId;
    private String aiCode;
    private String aiName;
    private String aiNameAr;
    private String modelName;
    private String apiUrl;
    private String systemPrompt;
    private Double temperature;
    private Integer contextSize;
    private String keepAlive;
    private Integer thinkEnabled;
    private Integer activeFlag;
    private String aiCreatedBy;
    private Date aiCreatedOn;
    private String aiUpdatedBy;
    private Date aiUpdatedOn;
}
