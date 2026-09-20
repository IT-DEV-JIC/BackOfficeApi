package com.jicjo.apis.dto.crossselling;

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
public class CsIgnoreListDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long ignoreId;
    private Long customerId;
    private String customerNo;
    private String sourceLob;
    private String targetLob;
    private Long ruleId;
    private String ignoreReason;
    private String createdBy;
    private Date createdDate;
    private Integer isActive;

}
