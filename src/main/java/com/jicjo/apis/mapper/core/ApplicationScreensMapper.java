package com.jicjo.apis.mapper.core;

import com.jicjo.apis.dto.core.ApplicationScreensDto;
import com.jicjo.apis.model.core.ApplicationScreens;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationScreensMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static ApplicationScreensDto toApplicationScreensDto(ApplicationScreens applicationScreens) {
        return new ApplicationScreensDto(
                applicationScreens.getScrId(),
                applicationScreens.getAppId(),
                applicationScreens.getScrName(),
                applicationScreens.getScrNameAr(),
                applicationScreens.getScrCreatedBy(),
                applicationScreens.getScrCreatedOn(),
                applicationScreens.getScrUpdatedBy(),
                applicationScreens.getScrUpdatedOn(),
                applicationScreens.getScrUrl()
        );
    }

    public static ApplicationScreens toApplicationScreens(ApplicationScreensDto applicationScreensDto) {
        return new ApplicationScreens(
                applicationScreensDto.getScrId(),
                applicationScreensDto.getAppId(),
                applicationScreensDto.getScrName(),
                applicationScreensDto.getScrNameAr(),
                applicationScreensDto.getScrCreatedBy(),
                applicationScreensDto.getScrCreatedOn(),
                applicationScreensDto.getScrUpdatedBy(),
                applicationScreensDto.getScrUpdatedOn(),
                applicationScreensDto.getScrUrl()
        );
    }

    public static List<ApplicationScreensDto> toApplicationScreensDtoList(List<ApplicationScreens> applicationScreens) {
        return applicationScreens.stream()
                .map(ApplicationScreensMapper::toApplicationScreensDto)
                .collect(Collectors.toList());
    }
}
