package com.jicjo.apis.service.core.impl;


import com.jicjo.apis.dto.core.EmailLogDto;
import com.jicjo.apis.mapper.core.EmailLogMapper;
import com.jicjo.apis.model.core.EmailLog;
import com.jicjo.apis.repository.core.EmailLogRepository;
import com.jicjo.apis.service.core.EmailSenderService;
import com.jicjo.apis.utility.GeneralMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.net.URISyntaxException;
import java.text.ParseException;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private EmailLogRepository emailLogRepository;

    @Override
    @Transactional
    public EmailLogDto sendEmail(EmailLogDto emailLogDto) throws ParseException, URISyntaxException {

        EmailLog emailLog = EmailLogMapper.toEmailLog(emailLogDto);
        emailLog.setId(null);
        emailLogRepository.save(emailLog);

        GeneralMailSender generalMailSender = new GeneralMailSender();
        generalMailSender.sendSimpleMessage(emailLogDto.getEmailSender(), emailLogDto.getEmailReceiver(),
                emailLogDto.getEmailCc(), emailLogDto.getEmailBcc(), emailLogDto.getEmailSubject(),
                emailLogDto.getEmailBody());

        return EmailLogMapper.toEmailLogDto(emailLog);
    }

    @Override
    @Transactional
    public EmailLogDto sendEmailAttachment(EmailLogDto emailLogDto) throws ParseException, URISyntaxException {
        EmailLog emailLog = EmailLogMapper.toEmailLog(emailLogDto);
        emailLog.setId(null);
        emailLogRepository.save(emailLog);
        //String attachmentName = "C:/Attachments/" + emailLogDto.getEmailAttachment();
        String attachmentName = "D:/TomcatApps/Attachments" + emailLogDto.getEmailAttachment();
        byte[] content = emailLogDto.getEmailAttachmentContent().getBytes();
        GeneralMailSender generalMailSender = new GeneralMailSender();
        generalMailSender.sendEmail(emailLogDto.getEmailSender(), emailLogDto.getEmailReceiver(),
                emailLogDto.getEmailCc(), emailLogDto.getEmailBcc(), emailLogDto.getEmailSubject(),
                emailLogDto.getEmailBody(),attachmentName,content);


        return EmailLogMapper.toEmailLogDto(emailLog);
    }
}
