package com.jicjo.apis.service.core;

import com.jicjo.apis.dto.core.EmailLogDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.ParseException;

@Service
public interface EmailSenderService extends Serializable {
    EmailLogDto sendEmail(EmailLogDto emailLogDto) throws ParseException, URISyntaxException;
    EmailLogDto sendEmailAttachment(EmailLogDto emailLogDto) throws ParseException, URISyntaxException;
}
