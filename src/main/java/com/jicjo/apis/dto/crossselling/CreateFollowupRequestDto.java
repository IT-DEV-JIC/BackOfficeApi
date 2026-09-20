package com.jicjo.apis.dto.crossselling;


import lombok.Data;

import java.util.Date;

@Data
public class CreateFollowupRequestDto {
    private String actionType;
    private String notes;
    private Date nextFollowupDate;
    private String createdBy;
}
