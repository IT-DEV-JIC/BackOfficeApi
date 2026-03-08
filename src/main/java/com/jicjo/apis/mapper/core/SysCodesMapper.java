package com.jicjo.apis.mapper.core;

import com.jicjo.apis.dto.core.SysCodesDto;
import com.jicjo.apis.model.core.SysCodes;

import java.io.Serial;
import java.io.Serializable;

public class SysCodesMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static SysCodesDto toSysCodesDto(SysCodes sysCodes) {
        return new SysCodesDto(
                sysCodes.getSysId(),
                sysCodes.getScType(),
                sysCodes.getScCode(),
                sysCodes.getScParentType(),
                sysCodes.getScParentCode(),
                sysCodes.getScAdesc(),
                sysCodes.getScLdesc(),
                sysCodes.getScNvalue(),
                sysCodes.getScVvalue(),
                sysCodes.getScCreatedBy(),
                sysCodes.getScCreatedOn(),
                sysCodes.getScUpdatedBy(),
                sysCodes.getScUpdatedOn(),
                sysCodes.getScFdesc(),
                sysCodes.getScServiceFlag(),
                sysCodes.getScVvalue2());
    }

    public static SysCodes toSysCodes(SysCodesDto sysCodesDto) {
        return new SysCodes(
                sysCodesDto.getSysId(),
                sysCodesDto.getScType(),
                sysCodesDto.getScCode(),
                sysCodesDto.getScParentType(),
                sysCodesDto.getScParentCode(),
                sysCodesDto.getScAdesc(),
                sysCodesDto.getScLdesc(),
                sysCodesDto.getScNvalue(),
                sysCodesDto.getScVvalue(),
                sysCodesDto.getScCreatedBy(),
                sysCodesDto.getScCreatedOn(),
                sysCodesDto.getScUpdatedBy(),
                sysCodesDto.getScUpdatedOn(),
                sysCodesDto.getScFdesc(),
                sysCodesDto.getScServiceFlag(),
                sysCodesDto.getScVvalue2());
    }
}
