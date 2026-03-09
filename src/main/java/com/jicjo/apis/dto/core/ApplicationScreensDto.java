package com.jicjo.apis.dto.core;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ApplicationScreensDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long scrId;
    private Long appId;
    private String scrName;
    private String scrNameAr;
    private String scrCreatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date scrCreatedOn;
    private String scrUpdatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date scrUpdatedOn;
    private String scrUrl;
}
