package com.jicjo.apis.utility;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;
import org.springframework.context.annotation.Bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Properties;

public class GeneralMailSender implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("mail.jicjo.com");
        mailSender.setPort(25);

        mailSender.setUsername("JIC");
        mailSender.setPassword("@1q2w3e4r");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Value("${attachments.directory}")
    private String attachmentsDirectory;

    public void sendSimpleMessage(String from,String to, String cC, String bCc,String subject, String text) {
        JavaMailSender emailSender = getJavaMailSender();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        if (!(cC.isEmpty() || cC == "")){
            message.setCc(cC);
        }
        if (!(bCc.isEmpty() || bCc == "")){
            message.setBcc(bCc);
        }
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendEmail(String from, String to, String cC, String bCc, String sub,String msgBody, String filename, byte[] content){
        JavaMailSender mailSender = getJavaMailSender();
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(to);
            if (!(cC.isEmpty() || cC == "")) {
                helper.setCc(cC);
            }
            if (!(bCc.isEmpty() || bCc == "")) {
                helper.setBcc(bCc);
            }
            helper.setSubject(sub);
            helper.setText(msgBody);
            //helper.addAttachment("C:/Attachments/" + filename, new ByteArrayResource(content));
            File file = new File(attachmentsDirectory, filename);
            file.getParentFile().mkdirs();
            helper.addAttachment( filename, file);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}