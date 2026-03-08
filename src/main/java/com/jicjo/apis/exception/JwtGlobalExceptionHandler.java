package com.jicjo.apis.exception;

import com.jicjo.apis.dto.core.EmailLogDto;
import com.jicjo.apis.dto.core.UsersDto;
import com.jicjo.apis.mapper.core.UsersMapper;
import com.jicjo.apis.model.core.ErrorLog;
import com.jicjo.apis.repository.core.ErrorLogRepository;
import com.jicjo.apis.repository.core.UsersRepository;
import com.jicjo.apis.service.core.EmailSenderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;



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

            EmailLogDto emailLogDto = new EmailLogDto();
            emailLogDto.setEmailSender("JIC@jicjo.com");
            emailLogDto.setEmailReceiver("IT@jicjo.com");
            emailLogDto.setEmailCc("");
            emailLogDto.setEmailBcc("");
            emailLogDto.setEmailSubject("Banca Error Notification");
            emailLogDto.setEmailBody("You have new error notification with Id " + log.getId() +
                    "\n \n" + Arrays.toString(ex.getStackTrace()));

            emailSenderService.sendEmail(emailLogDto);

        } catch (Exception e) {
            e.printStackTrace(); // <-- to see if saving the error itself fails
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred.");
    }

}
