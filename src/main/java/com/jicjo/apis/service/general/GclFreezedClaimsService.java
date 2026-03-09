package com.jicjo.apis.service.general;

import com.jicjo.apis.dto.general.GclFreezedClaimsDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public interface GclFreezedClaimsService extends Serializable {
    List<GclFreezedClaimsDto> getFreezedLog();
}
