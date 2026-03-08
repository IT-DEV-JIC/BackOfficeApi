package com.jicjo.apis.mapper.medical;

import com.jicjo.apis.dto.general.GclEkrookaClaimsDto;
import com.jicjo.apis.dto.medical.StpEmailTemplateDto;
import com.jicjo.apis.mapper.general.GclEkrookaClaimsMapper;
import com.jicjo.apis.model.general.GclEkrookaClaims;
import com.jicjo.apis.model.medical.StpEmailTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class StpEmailTemplateMapper {

    public static StpEmailTemplateDto toEmailTemplateDto(StpEmailTemplate stpEmailTemplate) {
        return new StpEmailTemplateDto(
                stpEmailTemplate.getStpEmtId(),
                stpEmailTemplate.getStpEmtConnect(),
                stpEmailTemplate.getStpEmtTo(),
                stpEmailTemplate.getStpEmtCc(),
                stpEmailTemplate.getStpEmtBcc(),
                stpEmailTemplate.getStpEmtTitle(),
                stpEmailTemplate.getStpEmtContents(),
                stpEmailTemplate.getStpEmtClntName(),
                stpEmailTemplate.getStpEmtCreatedBy(),
                stpEmailTemplate.getStpEmtCreatedOn(),
                stpEmailTemplate.getStpEmtUpdatedBy(),
                stpEmailTemplate.getStpEmtUpdatedOn()
        );
    }

    public static StpEmailTemplate toEmailTemplate(StpEmailTemplateDto stpEmailTemplateDto) {
        return new StpEmailTemplate(
                stpEmailTemplateDto.getStpEmtId(),
                stpEmailTemplateDto.getStpEmtConnect(),
                stpEmailTemplateDto.getStpEmtTo(),
                stpEmailTemplateDto.getStpEmtCc(),
                stpEmailTemplateDto.getStpEmtBcc(),
                stpEmailTemplateDto.getStpEmtTitle(),
                stpEmailTemplateDto.getStpEmtContents(),
                stpEmailTemplateDto.getStpEmtClntName(),
                stpEmailTemplateDto.getStpEmtCreatedBy(),
                stpEmailTemplateDto.getStpEmtCreatedOn(),
                stpEmailTemplateDto.getStpEmtUpdatedBy(),
                stpEmailTemplateDto.getStpEmtUpdatedOn()
        );
    }

    public static List<StpEmailTemplateDto> toStpEmailTemplateDtoList(List<StpEmailTemplate> stpEmailTemplate) {
        return stpEmailTemplate.stream()
                .map(StpEmailTemplateMapper::toEmailTemplateDto)
                .collect(Collectors.toList());
    }
}
