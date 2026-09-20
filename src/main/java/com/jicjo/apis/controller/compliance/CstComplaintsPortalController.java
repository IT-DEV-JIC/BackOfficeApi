package com.jicjo.apis.controller.compliance;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.jicjo.apis.dto.compliance.CstCmpRateDto;
import com.jicjo.apis.dto.compliance.CstComplaintsDto;
import com.jicjo.apis.dto.core.EmailLogDto;
import com.jicjo.apis.dto.core.OtpRequest;
import com.jicjo.apis.dto.core.SmsRequestDto;
import com.jicjo.apis.service.compliance.CstComplaintsService;
import com.jicjo.apis.service.core.ApiCollingService;
import com.jicjo.apis.service.core.EmailSenderService;
import com.jicjo.apis.service.core.impl.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sctCompliancesPortal")
public class CstComplaintsPortalController implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ApiCollingService apiCollingService;

    @Autowired
    private CstComplaintsService cstComplaintsService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private OtpService otpService;


    public CstComplaintsPortalController(ApiCollingService apiCollingService) {
        this.apiCollingService = apiCollingService;
    }

    @PostMapping("/sendOtp")
    public ResponseEntity<?> sendOtp(@RequestParam  String phoneNumber) {
        otpService.generateAndSendOtp(phoneNumber, phoneNumber,"");
        return ResponseEntity.ok(Map.of("status", "OTP_SENT"));
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request) {
        boolean valid = otpService.verifyOtp(request.getUsername(), request.getOtp());
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("errorCode", "INVALID_OTP"));
        }

        otpService.clearOtp(request.getUsername());
        return ResponseEntity.ok(Map.of("status", "AUTHORIZED"));
    }

    @PostMapping("/addCstCompliances")
    public ResponseEntity<?> addCstCompliances(@RequestBody CstComplaintsDto cstComplaintsDto,
                                               @RequestParam(defaultValue = "en") String lang) throws Exception {

        String cmpNo = cstComplaintsService.addPortalCstComplaintsService(cstComplaintsDto);

        String requestTypeAr;
        String requestTypeEn;

        switch (cstComplaintsDto.getCstCmpType().intValue()) {
            case 1:
                requestTypeAr = "الشكوى";
                requestTypeEn = "Complaint";
                break;
            case 2:
                requestTypeAr = "الاقتراح";
                requestTypeEn = "Suggestion";
                break;
            case 3:
                requestTypeAr = "الاستفسار";
                requestTypeEn = "Inquiry";
                break;
            default:
                requestTypeAr = "الطلب";
                requestTypeEn = "Request";
                break;
        }

        String customerName = String.join(" ",
                cstComplaintsDto.getCstCmpFirstName() != null ? cstComplaintsDto.getCstCmpFirstName().trim() : "",
                cstComplaintsDto.getCstCmpFatherName() != null ? cstComplaintsDto.getCstCmpFatherName().trim() : "",
                cstComplaintsDto.getCstCmpGrandfatherName() != null ? cstComplaintsDto.getCstCmpGrandfatherName().trim() : "",
                cstComplaintsDto.getCstCmpFamilyName() != null ? cstComplaintsDto.getCstCmpFamilyName().trim() : ""
        ).replaceAll("\\s+", " ").trim();

        boolean isArabic = "ar".equalsIgnoreCase(lang);

        if (customerName.isEmpty()) {
            customerName = isArabic ? "العميل" : "Customer";
        }

        String requestType = isArabic ? requestTypeAr : requestTypeEn;

        String emailSubject;
        String emailBody;
        String smsContent;
        String responseMessage;
        String rtl = "\u200F";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String creationDate = cstComplaintsDto.getCstCmpCreationDate() != null
                ? cstComplaintsDto.getCstCmpCreationDate().toInstant()
                  .atZone(ZoneId.systemDefault())
                  .toLocalDate()
                  .format(formatter)
                : "-";

        if (isArabic) {
            emailSubject = "شركة التأمين الأردنية - تأكيد استلام " + requestType + " - " + cmpNo;

            emailBody = rtl + "السيد/ السيدة " + customerName + "، تم استلام وتسجيل " + requestType + " بتاريخ " + creationDate + " تحت الرقم " + cmpNo  + " بخصوص " + cstComplaintsDto.getCstCmpTitle() + " وأن الحالة الحالية للشكوى: مفتوحة " + "حيث سيتم متابعتها من قبل الفريق المختص، وسيتم التواصل معكم عبر بياناتكم المسجلة. للاستفسار: " + "\n Email: JIC-Complaints@jicjo.com \n Phone: +962796381111"
                    + "\n\n" + "شركة التامين الأردنية";

            smsContent = rtl + "السيد/ السيدة " + customerName + "، تم استلام وتسجيل " + requestType + " بتاريخ " + creationDate + " تحت الرقم " + cmpNo  + " بخصوص " + cstComplaintsDto.getCstCmpTitle() + " وأن الحالة الحالية للشكوى: مفتوحة " + "حيث سيتم متابعتها من قبل الفريق المختص، وسيتم التواصل معكم عبر بياناتكم المسجلة. للاستفسار: " + "\n Email: JIC-Complaints@jicjo.com \n Phone: +962796381111"
                    + "\n\n" + "شركة التامين الأردنية";

            responseMessage = "تم إرسال الإشعارات بنجاح";
        } else {
            emailSubject = "Jordan Insurance Company - " + requestType + " Received - " + cmpNo;
            emailBody =
                    "Dear Mr./Ms. " + customerName + " we acknowledge receipt and registration of your " + requestType + " submitted on " + creationDate + " under reference number " +
                            cmpNo + " regarding  " + cstComplaintsDto.getCstCmpTitle() + ". Current status: open " + " Your complaint will be handled by the relevant team, and you will be contacted via your registered contact details. For inquiries: \n Email: JIC-Complaints@jicjo.com \n Phone: +962796381111"
                            + "\n\n" + "Jordan Insurance Company";

            smsContent = "Dear Mr./Ms. " + customerName + " we acknowledge receipt and registration of your " + requestType + " submitted on " + creationDate + " under reference number " +
                    cmpNo + " regarding  " + cstComplaintsDto.getCstCmpTitle() + ". Current status: open " + " Your complaint will be handled by the relevant team, and you will be contacted via your registered contact details. For inquiries: \n Email: JIC-Complaints@jicjo.com \n Phone: +962796381111";



            responseMessage = "Notifications sent successfully";
        }

        if (cstComplaintsDto.getCstCmpEmail() != null && !cstComplaintsDto.getCstCmpEmail().trim().isEmpty()) {
            EmailLogDto emailLogDto = new EmailLogDto();
            emailLogDto.setEmailSender("JIC@jicjo.com");
            emailLogDto.setEmailReceiver(cstComplaintsDto.getCstCmpEmail().trim());
            emailLogDto.setEmailCc("");
            emailLogDto.setEmailBcc("");
            emailLogDto.setEmailSubject(emailSubject);
            emailLogDto.setEmailBody(emailBody);
            emailSenderService.sendEmail(emailLogDto);
        }

        if (cstComplaintsDto.getCstCmpPhoneNumber() != null && !cstComplaintsDto.getCstCmpPhoneNumber().trim().isEmpty()) {
            List<String> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(cstComplaintsDto.getCstCmpPhoneNumber().trim());

            SmsRequestDto smsDto = new SmsRequestDto();
            smsDto.setService_type("bulk_sms");
            smsDto.setRecipient_numbers_type("single_numbers");
            smsDto.setPhone_numbers(phoneNumbers);
            smsDto.setContent(smsContent);
            smsDto.setSender_id("JIC");

            try {
                apiCollingService.SendSms(smsDto);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to send SMS", e);
            }
        }

        EmailLogDto emailLogDto = new EmailLogDto();
        emailLogDto.setEmailSender("JIC@jicjo.com");
        emailLogDto.setEmailReceiver("JIC-Complaints@jicjo.com");
        emailLogDto.setEmailCc("");
        emailLogDto.setEmailBcc("");
        emailLogDto.setEmailSubject("New request was open with number " + cmpNo);
        emailLogDto.setEmailBody("Dear Team, \n Kindly note that the new request was opened with number " + cmpNo + " with the following details \n" + cstComplaintsDto.getCstCmpBody() + "\n Regards,");
        emailSenderService.sendEmail(emailLogDto);

        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "complaintNumber", cmpNo,
                "lang", isArabic ? "ar" : "en",
                "message", responseMessage
        ));
    }

    @PostMapping(value = "/request-attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addRequestAttachment(
            @RequestParam("cstCmpNumber") String cstCmpNumber, @RequestPart(value = "requestFile") MultipartFile requestFile) {
        String result = cstComplaintsService.addRequestAttachment(cstCmpNumber, requestFile);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/addStars")
    public ResponseEntity<?> addStars(@RequestParam Long cstCmrId, @RequestParam Long cstCmrStars) {
        return ResponseEntity.ok(cstComplaintsService.addStars(cstCmrId,cstCmrStars));
    }

    @GetMapping("/findCstCmpRateByCstCmrId2")
    public ResponseEntity<CstCmpRateDto> findCstCmpRateByCstCmrId2(@RequestParam Long cstCmrId) {
        return ResponseEntity.ok(cstComplaintsService.findCstCmpRateByCstCmrId2(cstCmrId));
    }
}
