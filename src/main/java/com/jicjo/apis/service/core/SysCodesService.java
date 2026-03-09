package com.jicjo.apis.service.core;

import com.jicjo.apis.dto.core.SysCodesDto;
import org.springframework.stereotype.Service;

import java.util.List;

import java.io.Serializable;

@Service
public interface SysCodesService extends Serializable {
    List<SysCodesDto> getSysCodes(Long scType, Long scCode);
}
