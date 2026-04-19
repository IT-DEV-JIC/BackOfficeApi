package com.jicjo.apis.controller.compliance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jicjo.apis.dto.compliance.CstComplaintFollowupDto;
import com.jicjo.apis.dto.compliance.CstComplaintsDto;
import com.jicjo.apis.dto.core.EmailLogDto;
import com.jicjo.apis.dto.core.SmsRequestDto;
import com.jicjo.apis.service.compliance.CstComplaintsService;
import com.jicjo.apis.service.core.ApiCollingService;
import com.jicjo.apis.service.core.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cstComplaintsController")
public class CstComplaintsController {

    private final ApiCollingService apiCollingService;

    @Autowired
    private CstComplaintsService cstComplaintsService;

    @Autowired
    private EmailSenderService emailSenderService;

    public CstComplaintsController(ApiCollingService apiCollingService) {
        this.apiCollingService = apiCollingService;
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

        if (isArabic) {
            emailSubject = "شركة التأمين الأردنية - تأكيد استلام " + requestType + " - " + cmpNo;
            emailBody =
                    "السيد/السيدة: " + customerName + " المحترم/ة،\n\n" +
                            "تحية طيبة وبعد،\n\n" +
                            "نود إعلامكم بأنه تم استلام " + requestType + " الخاص بكم بنجاح، وذلك وفق التفاصيل التالية:\n\n" +
                            "رقم الطلب: " + cmpNo + "\n" +
                            "اسم العميل: " + customerName + "\n" +
                            "نوع الطلب: " + requestType + "\n\n" +
                            "سيتم مراجعة طلبكم من قبل الفريق المختص والعمل على معالجته في أقرب وقت ممكن وفقاً لإجراءات شركة التأمين الأردنية.\n\n" +
                            "في حال وجود أي استفسار أو لمتابعة الطلب، يرجى التواصل معنا على الرقم: +962796381111\n\n" +
                            "شاكرين حسن تعاونكم،،\n\n" +
                            "شركة التأمين الأردنية";

            smsContent = String.format(
                    "عزيزي/عزيزتي %s،\nتم استلام %s بنجاح.\nرقم الطلب: %s\nللتواصل وتتبع الطلب: %s\nشركة التأمين الأردنية",
                    customerName,
                    requestType,
                    cmpNo,
                    "+962796381111"
            );

            responseMessage = "تم إرسال الإشعارات بنجاح";
        } else {
            emailSubject = "Jordan Insurance Company - " + requestType + " Received - " + cmpNo;
            emailBody =
                    "Dear " + customerName + ",\n\n" +
                            "Greetings,\n\n" +
                            "We would like to inform you that your " + requestType + " has been successfully received with the following details:\n\n" +
                            "Request Number: " + cmpNo + "\n" +
                            "Customer Name: " + customerName + "\n" +
                            "Request Type: " + requestType + "\n\n" +
                            "Your request will be reviewed by the concerned team and handled as soon as possible in accordance with Jordan Insurance Company procedures.\n\n" +
                            "For inquiries or request tracking, please contact us at: +962796381111\n\n" +
                            "Best regards,\n\n" +
                            "Jordan Insurance Company";

            smsContent = String.format(
                    "Dear %s,\nYour %s has been received successfully.\nRequest No: %s\nFor inquiries & tracking: %s\nJordan Insurance Company",
                    customerName,
                    requestType,
                    cmpNo,
                    "+962796381111"
            );

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

        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "complaintNumber", cmpNo,
                "lang", isArabic ? "ar" : "en",
                "message", responseMessage
        ));
    }

    @PostMapping(value = "/addCstComplaintFollowup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addCstComplaintFollowup(@RequestPart("cstComplaintFollowupDto") String cstComplaintFollowupDtoJson,
                                                     @RequestPart(value = "requestFile", required = false) MultipartFile requestFile,
                                                     @RequestPart(value = "responceFile", required = false) MultipartFile responceFile
    ) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        CstComplaintFollowupDto cstComplaintFollowupDto = objectMapper.readValue(cstComplaintFollowupDtoJson, CstComplaintFollowupDto.class);

        cstComplaintsService.addCstComplaintFollowup(cstComplaintFollowupDto, requestFile, responceFile);

        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "message", "The followup was saved successfully"
        ));
    }

    @GetMapping("/getAllCstComplaints")
    public ResponseEntity<List<CstComplaintsDto>> getAllCstComplaints(@RequestParam(required = false)
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      Date fromDate,
                                                                      @RequestParam(required = false)
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                      Date toDate) {
        return ResponseEntity.ok(cstComplaintsService.getAllCstComplaints(fromDate,toDate));
    }

    @GetMapping("/findCstComplaintFollowupByCstCmpId")
    public ResponseEntity<List<CstComplaintFollowupDto>> findCstComplaintFollowupByCstCmpId(@RequestParam Long cstCmpId) {
        return ResponseEntity.ok(cstComplaintsService.findCstComplaintFollowupByCstCmpId(cstCmpId));
    }

    @PutMapping("/updatePortalCstComplaintsService")
    public ResponseEntity<?> updatePortalCstComplaintsService(@RequestBody CstComplaintsDto cstComplaintsDto) throws Exception {
        return ResponseEntity.ok(cstComplaintsService.updatePortalCstComplaintsService(cstComplaintsDto));
    }

    @GetMapping("/attachments/{filename}")
    public ResponseEntity<Resource> viewApplicationAttachment(@RequestParam String dir, @PathVariable String filename) {
        try {
            // UNC path to the shared folder
            String basePath = "D:/TomcatApps/Attachments/ComplaintsAttachments/" + dir + "/";
            Path filePath = Paths.get(basePath).resolve(filename).normalize();

            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
