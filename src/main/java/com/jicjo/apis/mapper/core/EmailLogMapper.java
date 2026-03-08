package com.jicjo.apis.mapper.core;

import com.jicjo.apis.dto.core.EmailLogDto;
import com.jicjo.apis.model.core.EmailLog;

import java.io.Serial;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class EmailLogMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static EmailLog toEmailLog(EmailLogDto emailLogDto) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(LocalDate.now().toString());

        return new EmailLog(
                emailLogDto.getId(),
                emailLogDto.getEmailSender(),
                emailLogDto.getEmailReceiver(),
                emailLogDto.getEmailCc(),
                emailLogDto.getEmailBcc(),
                emailLogDto.getEmailSubject(),
                emailLogDto.getEmailBody(),
                date
        );
    }

    public static EmailLogDto toEmailLogDto(EmailLog emailLog) {
        return new EmailLogDto(
                emailLog.getId(),
                emailLog.getEmailSender(),
                emailLog.getEmailReceiver(),
                emailLog.getEmailCc(),
                emailLog.getEmailBcc(),
                emailLog.getEmailSubject(),
                emailLog.getEmailBody(),
                "",
                "",
                emailLog.getCreationDate()
        );
    }
}
