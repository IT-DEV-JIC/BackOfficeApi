package com.jicjo.apis.controller.compliance;

import com.jicjo.apis.config.ClientInfoConfig;
import com.jicjo.apis.dto.compliance.EmpCompliancesDto;
import com.jicjo.apis.dto.core.EmailLogDto;
import com.jicjo.apis.service.compliance.EmpCompliancesService;
import com.jicjo.apis.service.core.EmailSenderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empCompliances")
public class EmpCompliancesController implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private EmpCompliancesService empCompliancesService;

    @Autowired
    private EmailSenderService emailSenderService;

    private final ClientInfoConfig clientInfoConfig;
    private final HttpServletRequest request;

    public EmpCompliancesController(ClientInfoConfig clientInfoConfig, HttpServletRequest request) {
        this.clientInfoConfig = clientInfoConfig;
        this.request = request;
    }

    @PostMapping("/addEmpCompliances")
    public ResponseEntity<?> addEmpCompliances(@RequestBody EmpCompliancesDto empCompliances) throws ParseException, URISyntaxException {
        EmpCompliancesDto empCompliancesIp = empCompliances;
        empCompliancesIp.setEmpCompliancesIp(this.clientInfoConfig.getClientIp());
        empCompliancesIp.setEmpCompliancesHost(clientInfoConfig.getClientHostName());
        empCompliancesIp.setEmpCompliancesWindowsUser(this.request.getRemoteUser());
        empCompliancesIp.setEmpCompliancesRequestUri(this.request.getRequestURI());

        empCompliancesService.addCompliance(empCompliancesIp);

        EmailLogDto emailLogDto = new EmailLogDto();

        emailLogDto.setEmailSender("ShakwaPortal@jicjo.com");
        emailLogDto.setEmailReceiver("IMT@jicjo.com");
        emailLogDto.setEmailCc("");
        emailLogDto.setEmailBcc("");
        emailLogDto.setEmailSubject(empCompliances.getEmpCompliancesTitle());
        emailLogDto.setEmailBody(empCompliances.getEmpCompliancesBody());

        emailSenderService.sendEmail(emailLogDto);

        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}