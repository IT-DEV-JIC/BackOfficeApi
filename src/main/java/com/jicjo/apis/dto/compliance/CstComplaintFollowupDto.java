package com.jicjo.apis.dto.compliance;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CstComplaintFollowupDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long cstCflId;
    private Long cstCmpId;
    private String cstCflNote;
    private Long cstCflStatusBefore;
    private Long cstCflStatusAfter;
    private Long cstCflActionType;
    private String cstCflAssignedTo;
    private String cstCflCreatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cstCflCreatedDate;
    private String cstCflRequestAttachment;
    private String cstCflResponcerAttachment;
}
