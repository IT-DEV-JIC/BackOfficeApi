package com.jicjo.apis.controller.core;

import com.jicjo.apis.dto.core.EmailLogDto;
import com.jicjo.apis.dto.core.ResponseDto;
import com.jicjo.apis.mapper.core.ResponseMapper;
import com.jicjo.apis.service.core.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.ParseException;

@AllArgsConstructor
@Repository
@ComponentScan
@EnableAutoConfiguration
@RestController
@CrossOrigin(origins = "*")
@Component
@RequestMapping("/core")
public class EmailSenderController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/SendEmail")
    public ResponseEntity<ResponseDto> sendEmail(@RequestBody EmailLogDto emailLogDto) throws ParseException, URISyntaxException {
        EmailLogDto savedEmailLog = emailSenderService.sendEmail(emailLogDto);
        return ResponseEntity.ok(ResponseMapper.toResponseDto(savedEmailLog));
    }

    @PostMapping("/sendEmailWithAttachment")
    public ResponseEntity<ResponseDto> sendEmailWithAttachment(@RequestBody EmailLogDto emailLogDto) throws ParseException, URISyntaxException {
        EmailLogDto savedEmailLog  = emailSenderService.sendEmailAttachment(emailLogDto);
        return ResponseEntity.ok(ResponseMapper.toResponseDto(savedEmailLog));
    }
}
