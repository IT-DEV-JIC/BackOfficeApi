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
public class GroupsDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long grbId;
    private Long grbType;
    private String grbDescription;
    private Boolean grbActive;
    private String grbEnGroupName;
    private String grbArGroupName;
    private String grbEnGroupDesc;
    private String grbArGroupDesc;
    private String grbTasksDistribution;
    private Integer grbWorkingDays;
    private String grbCreatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date grbCreatedOn;
    private String grbUpdatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date grbUpdatedOn;
}
