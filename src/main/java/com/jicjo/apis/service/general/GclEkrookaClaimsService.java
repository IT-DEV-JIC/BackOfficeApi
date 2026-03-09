package com.jicjo.apis.service.general;

import com.jicjo.apis.dto.general.GclEkrookaClaimsDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
public interface GclEkrookaClaimsService  {
    List<GclEkrookaClaimsDto> getGclEkrookaClaims(Date accidentDate);
}
