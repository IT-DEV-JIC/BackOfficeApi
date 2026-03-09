package com.jicjo.apis.dto.core;

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
public class EmailLogDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String emailSender;
    private String emailReceiver;
    private String emailCc;
    private String emailBcc;
    private String emailSubject;
    private String emailBody;
    private String emailAttachment;
    private String emailAttachmentContent;
    private Date creationDate;
}
