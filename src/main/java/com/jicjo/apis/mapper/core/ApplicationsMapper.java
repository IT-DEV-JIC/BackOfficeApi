package com.jicjo.apis.mapper.core;

import com.jicjo.apis.dto.core.ApplicationsDto;
import com.jicjo.apis.model.core.Applications;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationsMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static ApplicationsDto toApplicationsDto(Applications applications) {
        return new ApplicationsDto(
                applications.getAppId(),
                applications.getAppName(),
                applications.getAppNameAr(),
                applications.getAppLogo(),
                applications.getAppCreatedBy(),
                applications.getAppCreatedOn(),
                applications.getAppUpdatedBy(),
                applications.getAppUpdatedOn()
        );
    }

    public static Applications toApplications(ApplicationsDto applicationsDto) {
        return new Applications(
                applicationsDto.getAppId(),
                applicationsDto.getAppName(),
                applicationsDto.getAppNameAr(),
                applicationsDto.getAppLogo(),
                applicationsDto.getAppCreatedBy(),
                applicationsDto.getAppCreatedOn(),
                applicationsDto.getAppUpdatedBy(),
                applicationsDto.getAppUpdatedOn()
        );
    }

    public static List<ApplicationsDto> toApplicationsDtoList(List<Applications> applications) {
        return applications.stream()
                .map(ApplicationsMapper::toApplicationsDto)
                .collect(Collectors.toList());
    }
}
