package com.jicjo.apis.service.compliance;

import com.jicjo.apis.dto.compliance.CstComplaintFollowupDto;
import com.jicjo.apis.dto.compliance.CstComplaintsDto;
import com.jicjo.apis.model.compliance.CstComplaintFollowup;
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
}
