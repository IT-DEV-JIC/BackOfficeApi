package com.jicjo.apis.controller.compliance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jicjo.apis.dto.compliance.CstCmpRateDto;
import com.jicjo.apis.dto.compliance.CstComplaintFollowupDto;
import com.jicjo.apis.dto.compliance.CstComplaintsDashboardDto;
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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    @PostMapping(value = "/addCstComplaintFollowup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addCstComplaintFollowup(
            @RequestPart("cstComplaintFollowupDto") String cstComplaintFollowupDtoJson,
            @RequestPart(value = "requestFile", required = false) MultipartFile requestFile,
            @RequestPart(value = "responceFile", required = false) MultipartFile responceFile,
            @RequestParam(defaultValue = "ar") String lang,
            @RequestParam(required = false) String link
    ) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        CstComplaintFollowupDto dto =
                objectMapper.readValue(cstComplaintFollowupDtoJson, CstComplaintFollowupDto.class);

        // ================= SAVE FOLLOWUP =================
        cstComplaintsService.addCstComplaintFollowup(dto, requestFile, responceFile);

        // ================= GET BASE DATA =================
        CstComplaintsDto complaint = cstComplaintsService.getCstComplaintsById(dto.getCstCmpId());

        String cmpNo = complaint.getCstCmpNumber();

        boolean isArabic = "ar".equalsIgnoreCase(lang);

        String requestTypeAr;
        String requestTypeEn;

        switch (complaint.getCstCmpType().intValue()) {
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

        String requestType = isArabic ? requestTypeAr : requestTypeEn;

        String customerName = complaint.getCstCmpFirstName() + " " + complaint.getCstCmpFatherName()
                + " " + complaint.getCstCmpGrandfatherName() + " " + complaint.getCstCmpFamilyName() + " ";

        Long status = complaint.getCstCmpStatus(); // status بعد التحديث

        String emailSubject = "";
        String emailBody = "";
        String smsContent = "";
        String rtl = "\u200F";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String creationDate = complaint.getCstCmpCreationDate() != null
                ? complaint.getCstCmpCreationDate().toInstant()
                  .atZone(ZoneId.systemDefault())
                  .toLocalDate()
                  .format(formatter)
                : "-";
        if(complaint.getCstCmpType().intValue() == 1) {
            // ================= TEMPLATE LOGIC =================
            switch (status.intValue()) {

                // ================= OPEN =================
                case 1:
                    complaint.setCstCmpEmail(null);

                /*if (isArabic) {

                    emailSubject = "تحديث الشكوى - " + cmpNo;

                    emailBody = rtl + "السيد/ السيدة " + customerName + "، تم استلام وتسجيل " + requestType + " بتاريخ " + creationDate + " تحت الرقم " + cmpNo  + " بخصوص " + complaint.getCstCmpTitle() + " وأن الحالة الحالية للشكوى: مفتوحة " + "حيث سيتم متابعتها من قبل الفريق المختص، وسيتم التواصل معكم عبر بياناتكم المسجلة. للاستفسار: " + "complaints@jicjo.com/+962796381111";

                    smsContent = rtl + "السيد/ السيدة " + customerName + "، تم استلام وتسجيل " + requestType + " بتاريخ " + creationDate + " تحت الرقم " + cmpNo  + " بخصوص " + complaint.getCstCmpTitle() + " وأن الحالة الحالية للشكوى: مفتوحة " + "حيث سيتم متابعتها من قبل الفريق المختص، وسيتم التواصل معكم عبر بياناتكم المسجلة. للاستفسار: " + "complaints@jicjo.com/+962796381111";

                } else {

                    emailSubject = "Complaint Update - " + cmpNo;

                    emailBody =
                            "Dear Mr./Ms. " + customerName + " we acknowledge receipt and registration of your " + requestType + " submitted on " + creationDate + " under reference number " +
                                    cmpNo + " regarding  " + complaint.getCstCmpTitle() + ". Current status: open " + " Your complaint will be handled by the relevant team, and you will be contacted via your registered contact details. For inquiries: +962796381111/complaints@jicjo.com"
                                    + "\n\n" + "Jordan Insurance Company";

                    smsContent = "Dear Mr./Ms. " + customerName + " we acknowledge receipt and registration of your " + requestType + " submitted on " + creationDate + " under reference number " +
                            cmpNo + " regarding  " + complaint.getCstCmpTitle() + ". Current status: open " + " Your complaint will be handled by the relevant team, and you will be contacted via your registered contact details. For inquiries: +962796381111/complaints@jicjo.com";

                }*/
                    break;

                // ================= IN PROGRESS =================
                case 4:

                    if (isArabic) {

                        emailSubject = "تحديث حالة الشكوى - " + cmpNo;

                        emailBody = rtl + "السيد/ السيدة " + customerName + "، نود إعلامكم بأنه تم تحديث حالة " + requestType + " رقم " + cmpNo + " لتصبح قيد المعالجة سيتم متابعة الشكوى من قبل الجهة المختصة، وسيتم إشعاركم بأي مستجدات عبر بيانات الاتصال المسجلة. للاستفسار: " + "\n Email: JIC-Complaints@jicjo.com \n Phone: +962796381111"
                                + "\n" + "شركة التامين الأردنية";

                        smsContent = rtl + "السيد/ السيدة " + customerName + "، نود إعلامكم بأنه تم تحديث حالة " + requestType + " رقم " + cmpNo + " لتصبح قيد المعالجة سيتم متابعة الشكوى من قبل الجهة المختصة، وسيتم إشعاركم بأي مستجدات عبر بيانات الاتصال المسجلة. للاستفسار: " + "\n Email: JIC-Complaints@jicjo.com \n Phone: +962796381111"
                                + "\n" + "شركة التامين الأردنية";

                    } else {

                        emailSubject = "Complaint In Progress - " + cmpNo;

                        emailBody = "Dear Mr./Ms. " + customerName + " kindly note that your complaint " + cmpNo + " status has been updated to be In Progress. It is now under review by the relevant team. You will be notified of any updates. \n For inquiries: \n Email: JIC-Complaints@jicjo.com \n Phone: +962796381111 " + "\n\n" + "Jordan Insurance Company";

                        smsContent = "Dear Mr./Ms. " + customerName + " kindly note that your complaint " + cmpNo + " status has been updated to be In Progress. It is now under review by the relevant team. You will be notified of any updates. \n For inquiries: \n Email: JIC-Complaints@jicjo.com \n Phone: +962796381111 " + "\n\n" + "Jordan Insurance Company";
                    }
                    break;

                // ================= RESOLVED =================
                case 2:

                    if (isArabic) {

                        emailSubject = "تم حل الشكوى - " + cmpNo;

                        emailBody = rtl + "السيد/ السيدة " + customerName + "، نود إعلامكم بأنه تم تحديث حالة " + requestType + " رقم " + cmpNo + " لتصبح محلولة. وعليه، سيتم إغلاق الطلب نهائياً بعد قيامكم بتقييم الخدمة عبر الرابط التالي : " + link + " للاستفسار:  " + "\n complaints@jicjo.com/+962796381111"
                                + "\n" + "شركة التامين الأردنية";

                        smsContent = rtl + "السيد/ السيدة " + customerName + "، نود إعلامكم بأنه تم تحديث حالة " + requestType + " رقم " + cmpNo + " لتصبح محلولة. وعليه، سيتم إغلاق الطلب نهائياً بعد قيامكم بتقييم الخدمة عبر الرابط التالي : " + link + " للاستفسار:  " + "\n complaints@jicjo.com/+962796381111"
                                + "\n" + "شركة التامين الأردنية";


                    } else {

                        emailSubject = "Complaint Resolved - " + cmpNo;

                        emailBody = "Dear Mr./Ms. " + customerName + " kindly note that your complaint " + cmpNo + " status has been Resolved. The request will be closed permanently upon completion of the service evaluation via the following link." + link + " For inquiries: \n Email: JIC-Complaints@jicjo.com \n Phone: +962796381111 " + "\n\n" + "Jordan Insurance Company";

                        smsContent = "Dear Mr./Ms. " + customerName + " kindly note that your complaint " + cmpNo + " status has been Resolved. The request will be closed permanently upon completion of the service evaluation via the following link." + link + " For inquiries: \n Email: JIC-Complaints@jicjo.com \n Phone: +962796381111 " + "\n\n" + "Jordan Insurance Company";
                    }
                    break;

                // ================= CLOSED =================
                case 3:

                    if (isArabic) {

                        emailSubject = "إغلاق الشكوى - " + cmpNo;

                        emailBody =
                                "السيد/السيدة " + customerName + " المحترم/ة،\n\n" +
                                        "تم إغلاق الشكوى رقم " + cmpNo +
                                        " بعد إضافة المتابعة وإتمام المعالجة.\n\n" +
                                        "للاستفسار: [رقم الهاتف/البريد الإلكتروني للقسم]."
                                        + "\n" + "شركة التامين الأردنية";

                        smsContent =
                                "تم إغلاق شكواك رقم " + cmpNo + "."
                                        + "\n" + "شركة التامين الأردنية";

                    } else {

                        emailSubject = "Complaint Closed - " + cmpNo;

                        emailBody =
                                "Dear Mr./Ms. " + customerName + ",\n\n" +
                                        "your complaint " + cmpNo +
                                        " has been closed after follow-up processing."
                                        + "\n\n" + "Jordan Insurance Company";

                        smsContent =
                                "Complaint " + cmpNo + " has been closed."
                                        + "\n\n" + "Jordan Insurance Company";
                    }
                    break;

                default:
                    emailSubject = "Complaint Update - " + cmpNo;
                    emailBody = "Status updated.";
                    smsContent = "Complaint updated.";
            }

            // ================= SEND EMAIL =================
            if (complaint.getCstCmpEmail() != null && !complaint.getCstCmpEmail().trim().isEmpty()) {

                EmailLogDto emailLogDto = new EmailLogDto();
                emailLogDto.setEmailSender("JIC@jicjo.com");
                emailLogDto.setEmailReceiver(complaint.getCstCmpEmail().trim());
                emailLogDto.setEmailCc("");
                emailLogDto.setEmailBcc("");
                emailLogDto.setEmailSubject(emailSubject);
                emailLogDto.setEmailBody(emailBody);

                emailSenderService.sendEmail(emailLogDto);
            }
        }

        // ================= SEND SMS =================
        if (complaint.getCstCmpPhoneNumber() != null && !complaint.getCstCmpPhoneNumber().trim().isEmpty()) {

            List<String> phones = new ArrayList<>();
            phones.add(complaint.getCstCmpPhoneNumber().trim());

            SmsRequestDto smsDto = new SmsRequestDto();
            smsDto.setService_type("bulk_sms");
            smsDto.setRecipient_numbers_type("single_numbers");
            smsDto.setPhone_numbers(phones);
            smsDto.setContent(smsContent);
            smsDto.setSender_id("JIC");

            apiCollingService.SendSms(smsDto);
        }

        if (dto.getCstCflAssignedTo() != null && !dto.getCstCflAssignedTo().trim().isEmpty()) {
            EmailLogDto emailLogDto = new EmailLogDto();
            emailLogDto.setEmailSender("JIC@jicjo.com");
            emailLogDto.setEmailReceiver(dto.getCstCflAssignedTo());
            emailLogDto.setEmailCc("JIC-Complaints@jicjo.com");
            emailLogDto.setEmailBcc("");
            emailLogDto.setEmailSubject("Change on Request Number " + cmpNo);
            emailLogDto.setEmailBody("Dear " + dto.getCstCflAssignedTo() + ", \n Kindly note that the new request number " + cmpNo + " was assigned to you have new followup notification \n"  + "\n Regards,");
            emailSenderService.sendEmail(emailLogDto);
        }


        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "message", "The followup was saved successfully"
        ));
    }
    /*public ResponseEntity<?> addCstComplaintFollowup(@RequestPart("cstComplaintFollowupDto") String cstComplaintFollowupDtoJson,
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
    }*/

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

    @GetMapping("/getCstComplaintsDashboar")
    public ResponseEntity<List<CstComplaintsDashboardDto>> getCstComplaintsDashboar() {
        return ResponseEntity.ok(cstComplaintsService.getCstComplaintsDashboar());
    }

    @PostMapping("/addCstCmpRate")
    public ResponseEntity<?> addCstCmpRate(@RequestBody CstCmpRateDto cstCmpRateDto) {
        return ResponseEntity.ok(cstComplaintsService.addCstCmpRate(cstCmpRateDto));
    }

    @GetMapping("/findCstCmpRateByCstCmpId")
    public ResponseEntity<CstCmpRateDto> findCstCmpRateByCstCmpId(@RequestParam Long cstCmpId) {
        return ResponseEntity.ok(cstComplaintsService.findCstCmpRateByCstCmpId(cstCmpId));
    }
}
