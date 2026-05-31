package com.jicjo.apis.mapper.compliance;

import com.jicjo.apis.dto.compliance.CstCmpRateDto;
import com.jicjo.apis.model.compliance.CstCmpRate;

import java.io.Serial;
import java.io.Serializable;

public class CstCmpRateMapper implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static CstCmpRateDto toCstCmpRateDto(CstCmpRate cstCmpRate) {
        return new CstCmpRateDto(
                cstCmpRate.getCstCmrId(),
                cstCmpRate.getCstCmpId(),
                cstCmpRate.getCstCdoUser(),
                cstCmpRate.getCstCmrStars(),
                cstCmpRate.getCstCmrCreationDate()
        );
    }

    public static CstCmpRate toCstCmpRate(CstCmpRateDto cstCmpRateDto) {
        return new CstCmpRate(
                cstCmpRateDto.getCstCmrId(),
                cstCmpRateDto.getCstCmpId(),
                cstCmpRateDto.getCstCdoUser(),
                cstCmpRateDto.getCstCmrStars(),
                cstCmpRateDto.getCstCmrCreationDate()
        );
    }
}
