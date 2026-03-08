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
public class GroupMembersDto  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long grbId;
    private String grbMemMember;
    private String grbMemCreatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date grbMemCreatedOn;
    private String grbMemUpdatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date grbMemUpdatedOn;
    private Long grpId;
}
