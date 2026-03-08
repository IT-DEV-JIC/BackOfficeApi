package com.jicjo.apis.service.medical;


import com.jicjo.apis.dto.medical.StpEmailTemplateDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public interface StpEmailTemplateService extends Serializable {
    StpEmailTemplateDto saveStpEmailTemplate(StpEmailTemplateDto stpEmailTemplateDto);
    StpEmailTemplateDto updateStpEmailTemplate(StpEmailTemplateDto stpEmailTemplateDto);
    StpEmailTemplateDto getStpEmailTemplate(Long stpEmtId);
    List<StpEmailTemplateDto> getStpEmailTemplateByParent(Long stpEmtConnectBy);
}
