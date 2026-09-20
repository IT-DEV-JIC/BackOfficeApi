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
public class CsOpportunityFollowupDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long opportunityId;
    private String actionType;
    private String notes;
    private Date followupDate;
    private Date nextFollowupDate;
    private String createdBy;
    private Date createdDate;
}
