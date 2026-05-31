package com.jicjo.apis.service.compliance.impl;

import com.jicjo.apis.dto.compliance.CstCmpRateDto;
import com.jicjo.apis.dto.compliance.CstComplaintFollowupDto;
import com.jicjo.apis.dto.compliance.CstComplaintsDashboardDto;
import com.jicjo.apis.dto.compliance.CstComplaintsDto;
import com.jicjo.apis.mapper.compliance.CstCmpRateMapper;
import com.jicjo.apis.mapper.compliance.CstComplaintFollowupMapper;
import com.jicjo.apis.mapper.compliance.CstComplaintsMapper;
import com.jicjo.apis.model.compliance.CstCmpRate;
import com.jicjo.apis.model.compliance.CstComplaintFollowup;
import com.jicjo.apis.model.compliance.CstComplaints;
import com.jicjo.apis.repository.compliance.*;
import com.jicjo.apis.service.compliance.CstComplaintsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serial;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CstComplaintsServiceImpl implements CstComplaintsService {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    CodeSegmentationRepository  codeSegmentationRepository;

    @Autowired
    private CstComplaintsRepository cstComplaintsRepository;

    @Autowired
    private CstComplaintFollowupRepository cstComplaintFollowupRepository;

    @Autowired
    private CstComplaintsDashboardRepository cstComplaintsDashboardRepository;

    @Autowired
    private CstCmpRateRepository cstCmpRateRepository;

    private static final String REQUEST_DIR = "D:/TomcatApps/Attachments/ComplaintsAttachments/Request";
    private static final String RESPONCE_DIR = "D:/TomcatApps/Attachments/ComplaintsAttachments/Responce";

    @Override
    @Transactional
    public String addPortalCstComplaintsService(CstComplaintsDto cstComplaintsDto) {

        cstComplaintsDto.setCstCmpNumber(codeSegmentationRepository.getCstComplaintNumber(cstComplaintsDto.getCstCmpType()));
        CstComplaints savedCstComplaints = cstComplaintsRepository.save(CstComplaintsMapper.toCstComplaints(cstComplaintsDto));

        CstComplaintFollowup cstComplaintFollowup  = new CstComplaintFollowup();
        cstComplaintFollowup.setCstCflId(null);
        cstComplaintFollowup.setCstCmpId(savedCstComplaints.getCstCmpId());
        cstComplaintFollowup.setCstCflNote(savedCstComplaints.getCstCmpResponse());
        cstComplaintFollowup.setCstCflStatusBefore(savedCstComplaints.getCstCmpStatus());
        cstComplaintFollowup.setCstCflStatusAfter(savedCstComplaints.getCstCmpStatus());
        cstComplaintFollowup.setCstCflActionType(1L);
        cstComplaintFollowup.setCstCflAssignedTo(savedCstComplaints.getCstCdoUser());
        cstComplaintFollowup.setCstCflCreatedBy(savedCstComplaints.getCstCmpCreatedBy());
        cstComplaintFollowup.setCstCflCreatedDate(savedCstComplaints.getCstCmpCreationDate());
        cstComplaintFollowup.setCstCmpPriorityAfter(savedCstComplaints.getCstCmpPriority());
        cstComplaintFollowup.setCstCmpPriorityBefore(savedCstComplaints.getCstCmpPriority());
        cstComplaintFollowupRepository.save(cstComplaintFollowup);

        return cstComplaintsDto.getCstCmpNumber();
    }

    @Override
    public String addCstComplaintFollowup(
            CstComplaintFollowupDto cstComplaintFollowupDto,
            MultipartFile requestFile,
            MultipartFile responceFile
    ) {

        try {

            // ================= REQUEST ATTACHMENT =================
            if (requestFile != null && !requestFile.isEmpty()) {

                File requestFolder = new File(REQUEST_DIR);
                if (!requestFolder.exists()) {
                    requestFolder.mkdirs();
                }

                String originalName = requestFile.getOriginalFilename();
                String safeName = originalName != null ? originalName.replaceAll("\\s+", "_") : "file";

                String requestFileName =
                        System.currentTimeMillis() + "_REQ_" + UUID.randomUUID() + "_" + safeName;

                File requestDestination = new File(REQUEST_DIR, requestFileName);
                requestFile.transferTo(requestDestination);

                cstComplaintFollowupDto.setCstCflRequestAttachment(requestFileName);
            }


            // ================= RESPONSE ATTACHMENT =================
            if (responceFile != null && !responceFile.isEmpty()) {

                File responceFolder = new File(RESPONCE_DIR);
                if (!responceFolder.exists()) {
                    responceFolder.mkdirs();
                }

                String originalName = responceFile.getOriginalFilename();
                String safeName = originalName != null ? originalName.replaceAll("\\s+", "_") : "file";

                String responceFileName =
                        System.currentTimeMillis() + "_RES_" + UUID.randomUUID() + "_" + safeName;

                File responceDestination = new File(RESPONCE_DIR, responceFileName);
                responceFile.transferTo(responceDestination);

                cstComplaintFollowupDto.setCstCflResponcerAttachment(responceFileName);
            }

            // ================= SAVE TO DATABASE =================
            cstComplaintFollowupRepository.save(CstComplaintFollowupMapper.toCstComplaintFollowup(cstComplaintFollowupDto));
            return "Saved Successfully";

        } catch (Exception e) {
            throw new RuntimeException("Error uploading followup attachments: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public String updatePortalCstComplaintsService(CstComplaintsDto cstComplaintsDto) {
        CstComplaints selectedCstComplaints = cstComplaintsRepository.getCstComplaintsByCstCmpId(cstComplaintsDto.getCstCmpId()).orElseThrow(()

                -> new RuntimeException("Complaint Not Found"));

        selectedCstComplaints.setCstCmpNumber(cstComplaintsDto.getCstCmpNumber());
        selectedCstComplaints.setCstCmpType(cstComplaintsDto.getCstCmpType());
        selectedCstComplaints.setCstCmpFirstName(cstComplaintsDto.getCstCmpFirstName());
        selectedCstComplaints.setCstCmpFatherName(cstComplaintsDto.getCstCmpFatherName());
        selectedCstComplaints.setCstCmpGrandfatherName(cstComplaintsDto.getCstCmpGrandfatherName());
        selectedCstComplaints.setCstCmpFamilyName(cstComplaintsDto.getCstCmpFamilyName());
        selectedCstComplaints.setCstCmpPhoneNumber(cstComplaintsDto.getCstCmpPhoneNumber());
        selectedCstComplaints.setCstCmpEmail(cstComplaintsDto.getCstCmpEmail());
        selectedCstComplaints.setCstCmpTitle(cstComplaintsDto.getCstCmpTitle());
        selectedCstComplaints.setCstCmpBody(cstComplaintsDto.getCstCmpBody());
        selectedCstComplaints.setCstCmpCreationDate(cstComplaintsDto.getCstCmpCreationDate());
        selectedCstComplaints.setCstCmpUpdateBy(cstComplaintsDto.getCstCmpUpdateBy());
        selectedCstComplaints.setCstCmpUpdateDate(cstComplaintsDto.getCstCmpUpdateDate());
        selectedCstComplaints.setCstCdoUser(cstComplaintsDto.getCstCdoUser());
        selectedCstComplaints.setCstCmpPriority(cstComplaintsDto.getCstCmpPriority());
        selectedCstComplaints.setCstCmpSource(cstComplaintsDto.getCstCmpSource());
        selectedCstComplaints.setCstCmpStatus(cstComplaintsDto.getCstCmpStatus());
        selectedCstComplaints.setCstCmpStatusDate(cstComplaintsDto.getCstCmpStatusDate());
        selectedCstComplaints.setCstCmpResponse(cstComplaintsDto.getCstCmpResponse());
        selectedCstComplaints.setCstCmpResolution(cstComplaintsDto.getCstCmpResolution());
        selectedCstComplaints.setCstCmpAssignmentDate(cstComplaintsDto.getCstCmpAssignmentDate());
        selectedCstComplaints.setCstCmpStartDate(cstComplaintsDto.getCstCmpStartDate());
        selectedCstComplaints.setCstCmpEndDate(cstComplaintsDto.getCstCmpEndDate());
        selectedCstComplaints.setCstCmpInsuranceType(cstComplaintsDto.getCstCmpInsuranceType());

        cstComplaintsRepository.save(selectedCstComplaints);

        return "Saved";
    }

    @Override
    public List<CstComplaintsDto> getAllCstComplaints(Date fromDate, Date toDate) {
        return CstComplaintsMapper.toCstComplaintsDtoList(cstComplaintsRepository.getAllCstComplaints(fromDate,toDate));
    }

    @Override
    public List<CstComplaintFollowupDto> findCstComplaintFollowupByCstCmpId(Long cstCmpId) {
        return CstComplaintFollowupMapper.toCstComplaintFollowupDtoList(cstComplaintFollowupRepository.findCstComplaintFollowupByCstCmpId(cstCmpId));
    }

    @Override
    public List<CstComplaintsDashboardDto> getCstComplaintsDashboar() {
        return this.cstComplaintsDashboardRepository.getCstComplaintsDashboar();
    }

    @Override
    public CstComplaintsDto getCstComplaintsById(Long cstCmpId) {
        return CstComplaintsMapper.toCstComplaintsDto(cstComplaintsRepository.getCstComplaintsByCstCmpId(cstCmpId).orElseThrow(()
                -> new RuntimeException("Complaint Not Found")));
    }

    @Override
    public CstCmpRateDto addCstCmpRate(CstCmpRateDto cstCmpRateDto) {
        CstCmpRate cstCmpRate = CstCmpRateMapper.toCstCmpRate(cstCmpRateDto);

        cstCmpRate.setCstCmrId(null);
        cstCmpRate.setCstCmrCreationDate(new Date());
        return CstCmpRateMapper.toCstCmpRateDto(cstCmpRateRepository.save(cstCmpRate));
    }

    @Override
    public String addStars(Long cstCmrId, Long cstCmrStars) {
        CstCmpRate cstCmpRate = cstCmpRateRepository.findCstCmpRateByCstCmrId(cstCmrId).orElseThrow(()
                -> new RuntimeException("Complaint Rate Not Found"));
        cstCmpRate.setCstCmrStars(cstCmrStars);
        cstCmpRateRepository.save(cstCmpRate);

        return "Saved";
    }

    @Override
    public CstCmpRateDto findCstCmpRateByCstCmpId(Long cstCmpId) {
        return CstCmpRateMapper.toCstCmpRateDto(cstCmpRateRepository.findCstCmpRateByCstCmpId(cstCmpId).orElseThrow(()
                -> new RuntimeException("Rate Not Found")));
    }

    @Override
    public CstCmpRateDto findCstCmpRateByCstCmrId2(Long cstCmrId) {
        return CstCmpRateMapper.toCstCmpRateDto(cstCmpRateRepository.findCstCmpRateByCstCmrId2(cstCmrId).orElseThrow(()
                -> new RuntimeException("Rate Not Found")));
    }
}
