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
public class GroupScreensDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long gsrId;
    private Long appId;
    private Long scrId;
    private Long csrId;
    private String gsrClntName;
    private String gsrCreatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date gsrCreatedOn;
    private String gsrUpdatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date gsrUpdatedOn;
}
