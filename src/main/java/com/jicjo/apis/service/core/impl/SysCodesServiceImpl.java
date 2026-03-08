package com.jicjo.apis.service.core.impl;

import com.jicjo.apis.dto.core.SysCodesDto;
import com.jicjo.apis.mapper.core.SysCodesMapper;
import com.jicjo.apis.model.core.SysCodes;
import com.jicjo.apis.repository.core.SysCodesRepository;
import com.jicjo.apis.service.core.SysCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysCodesServiceImpl implements SysCodesService {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private SysCodesRepository sysCodesRepository;

    @Override
    @Transactional
    public List<SysCodesDto> getSysCodes(Long scType, Long scCode) {
        List<SysCodesDto> sysCodesDtos = new ArrayList<>();
        for (SysCodes sysCodes: sysCodesRepository.getSysCodes(scType, scCode)){
            sysCodesDtos.add(SysCodesMapper.toSysCodesDto(sysCodes));
        }
        return sysCodesDtos;
    }
}
