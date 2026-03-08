package com.jicjo.apis.mapper.general;

import com.jicjo.apis.dto.general.GclEkrookaClaimsDto;
import com.jicjo.apis.model.general.GclEkrookaClaims;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class GclEkrookaClaimsMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static GclEkrookaClaimsDto toGclEkrookaClaimsDto(GclEkrookaClaims gclEkrookaClaims){
        return new GclEkrookaClaimsDto(
                gclEkrookaClaims.getId(),
                gclEkrookaClaims.getKrokaNo(),
                gclEkrookaClaims.getAccidentId(),
                gclEkrookaClaims.getAccidentDate(),
                gclEkrookaClaims.getTrafficSergentNo(),
                gclEkrookaClaims.getTrafficSergentName(),
                gclEkrookaClaims.getJordanGovernorateId(),
                gclEkrookaClaims.getGovernorateDesc(),
                gclEkrookaClaims.getVehicleCount(),
                gclEkrookaClaims.getAccidentPrimaryType(),
                gclEkrookaClaims.getAccidentPrimaryTypeDesc(),
                gclEkrookaClaims.getAccidentSubType(),
                gclEkrookaClaims.getAccidentSubTypeDesc(),
                gclEkrookaClaims.getAccidentOrangeSerialNo(),
                gclEkrookaClaims.getLongitude(),
                gclEkrookaClaims.getLatitude(),
                gclEkrookaClaims.getAccidentHurtCount(),
                gclEkrookaClaims.getIsActive(),
                gclEkrookaClaims.getSecurityCenterNo(),
                gclEkrookaClaims.getSecurityCenterNoDesc(),
                gclEkrookaClaims.getStatus(),
                gclEkrookaClaims.getStatusDescription()
        );
    }

    public static GclEkrookaClaims toGclEkrookaClaims(GclEkrookaClaimsDto gclEkrookaClaimsDto){
        return new GclEkrookaClaims(
                gclEkrookaClaimsDto.getId(),
                gclEkrookaClaimsDto.getKrokaNo(),
                gclEkrookaClaimsDto.getAccidentId(),
                gclEkrookaClaimsDto.getAccidentDate(),
                gclEkrookaClaimsDto.getTrafficSergentNo(),
                gclEkrookaClaimsDto.getTrafficSergentName(),
                gclEkrookaClaimsDto.getJordanGovernorateId(),
                gclEkrookaClaimsDto.getGovernorateDesc(),
                gclEkrookaClaimsDto.getVehicleCount(),
                gclEkrookaClaimsDto.getAccidentPrimaryType(),
                gclEkrookaClaimsDto.getAccidentPrimaryTypeDesc(),
                gclEkrookaClaimsDto.getAccidentSubType(),
                gclEkrookaClaimsDto.getAccidentSubTypeDesc(),
                gclEkrookaClaimsDto.getAccidentOrangeSerialNo(),
                gclEkrookaClaimsDto.getLongitude(),
                gclEkrookaClaimsDto.getLatitude(),
                gclEkrookaClaimsDto.getAccidentHurtCount(),
                gclEkrookaClaimsDto.getIsActive(),
                gclEkrookaClaimsDto.getSecurityCenterNo(),
                gclEkrookaClaimsDto.getSecurityCenterNoDesc(),
                gclEkrookaClaimsDto.getStatus(),
                gclEkrookaClaimsDto.getStatusDescription()
        );
    }

    public static List<GclEkrookaClaimsDto> toGclEkrookaClaimsDtoList(List<GclEkrookaClaims> gclEkrookaClaims) {
        return gclEkrookaClaims.stream()
                .map(GclEkrookaClaimsMapper::toGclEkrookaClaimsDto)
                .collect(Collectors.toList());
    }
}
