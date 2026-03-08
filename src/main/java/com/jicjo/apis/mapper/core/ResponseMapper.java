package com.jicjo.apis.mapper.core;

import com.jicjo.apis.dto.core.EmailLogDto;
import com.jicjo.apis.dto.core.ResponseDto;

import java.io.Serial;
import java.io.Serializable;

public class ResponseMapper  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static ResponseDto toResponseDto(EmailLogDto emailLogDto){
        return new ResponseDto(
                emailLogDto.getEmailSender(),
                emailLogDto.getEmailReceiver(),
                emailLogDto.getEmailCc(),
                emailLogDto.getEmailBcc(),
                emailLogDto.getEmailSubject(),
                emailLogDto.getEmailBody(),
                emailLogDto.getCreationDate()
        );
    }
}
