package com.jicjo.apis.exception;

import com.jicjo.apis.dto.core.EmailLogDto;
import com.jicjo.apis.model.core.ErrorLog;
import com.jicjo.apis.repository.core.ErrorLogRepository;
import com.jicjo.apis.service.core.EmailSenderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@Qualifier("jwtExceptionHandler")
@Component("jwtGlobalExceptionHandler")
public class JwtGlobalExceptionHandler implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private ErrorLogRepository errorLogRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex, HttpServletRequest request) {
        System.out.println("💥 GlobalExceptionHandler triggered: " + ex.getMessage());

        try {
            ErrorLog log = new ErrorLog();
            log.setExceptionType(ex.getClass().getName());
            log.setMessage(ex.getMessage());
            log.setStackTrace(Arrays.toString(ex.getStackTrace()));
            log.setPath(request.getRequestURI());
            log.setTimestamp(new Date());

            log = errorLogRepository.save(log);

            if (shouldSendEmail(ex)) {
                EmailLogDto emailLogDto = new EmailLogDto();
                emailLogDto.setEmailSender("PortalAdmin@jicjo.com");
                emailLogDto.setEmailReceiver("IT@jicjo.com");
                emailLogDto.setEmailCc("");
                emailLogDto.setEmailBcc("");
                emailLogDto.setEmailSubject("Back Office Portal Error Notification");
                emailLogDto.setEmailBody("You have new error notification with Id " + log.getId() +
                        "\n \n" + Arrays.toString(ex.getStackTrace()));

                emailSenderService.sendEmail(emailLogDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred.");
    }

    private boolean shouldSendEmail(Exception ex) {
        String exceptionName = ex.getClass().getName();
        List<String> ignoredExceptions = Arrays.asList(
                "org.apache.catalina.connector.ClientAbortException",
                "java.io.IOException",
                "org.springframework.web.servlet.resource.NoResourceFoundException",
                "org.springframework.web.HttpRequestMethodNotSupportedException",
                "org.springframework.security.access.AccessDeniedException"
        );

        return !ignoredExceptions.contains(exceptionName);
    }
}