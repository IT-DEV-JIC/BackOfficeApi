package com.jicjo.apis.dto.medical;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
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
public class StpEmailTemplateDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long stpEmtId;
    private Long stpEmtConnect;
    @NotNull
    private String stpEmtTo;
    private String stpEmtCc;
    private String stpEmtBcc;
    @NotNull
    private String stpEmtTitle;
    @NotNull
    private String stpEmtContents;
    @NotNull
    private String stpEmtClntName;
    @NotNull
    private String stpEmtCreatedBy;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String stpEmtCreatedOn;
    private String stpEmtUpdatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String stpEmtUpdatedOn;
}
