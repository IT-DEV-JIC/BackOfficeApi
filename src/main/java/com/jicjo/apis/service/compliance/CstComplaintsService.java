package com.jicjo.apis.service.compliance;

import com.jicjo.apis.dto.compliance.CstCmpRateDto;
import com.jicjo.apis.dto.compliance.CstComplaintFollowupDto;
import com.jicjo.apis.dto.compliance.CstComplaintsDashboardDto;
import com.jicjo.apis.dto.compliance.CstComplaintsDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
public interface CstComplaintsService extends Serializable {
    String addPortalCstComplaintsService(CstComplaintsDto cstComplaintsDto);
    String addCstComplaintFollowup(CstComplaintFollowupDto cstComplaintFollowupDto, MultipartFile requestFile, MultipartFile responceFile);

    String updatePortalCstComplaintsService(CstComplaintsDto cstComplaintsDto);

    List<CstComplaintsDto> getAllCstComplaints(Date fromDate, Date toDate);
    List<CstComplaintFollowupDto> findCstComplaintFollowupByCstCmpId(Long cstCmpId);
    List<CstComplaintsDashboardDto> getCstComplaintsDashboar();
    CstComplaintsDto  getCstComplaintsById(Long cstCmpId);
    CstCmpRateDto addCstCmpRate(CstCmpRateDto cstCmpRateDto);
    String addStars(Long cstCmrId, Long cstCmrStars);
    CstCmpRateDto findCstCmpRateByCstCmpId(Long cstCmpId);
    CstCmpRateDto findCstCmpRateByCstCmrId2(Long cstCmrId);
    String addRequestAttachment(String cstCmpNumber, MultipartFile requestFile);
}
