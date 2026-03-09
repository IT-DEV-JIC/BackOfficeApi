package com.jicjo.apis.dto.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SysCodesDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String sysId;
    private Long scType;
    private Long scCode;
    private Long scParentType;
    private Long scParentCode;
    private String scAdesc;
    private String scLdesc;
    private Double scNvalue;
    private String scVvalue;
    private String scCreatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date scCreatedOn;
    private String scUpdatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date scUpdatedOn;
    private String scFdesc;
    private Integer scServiceFlag;
    private String scVvalue2;
}
