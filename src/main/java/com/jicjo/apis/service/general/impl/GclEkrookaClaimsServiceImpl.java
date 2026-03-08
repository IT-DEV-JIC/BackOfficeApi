package com.jicjo.apis.service.general.impl;

import com.jicjo.apis.dto.general.GclEkrookaClaimsDto;
import com.jicjo.apis.mapper.general.GclEkrookaClaimsMapper;
import com.jicjo.apis.repository.general.GclEkrookaClaimsRepository;
import com.jicjo.apis.service.general.GclEkrookaClaimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.util.Date;
import java.util.List;

@Service
public class GclEkrookaClaimsServiceImpl implements GclEkrookaClaimsService {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private GclEkrookaClaimsRepository gclEkrookaClaimsRepository;

    @Override
    @Transactional
    public List<GclEkrookaClaimsDto> getGclEkrookaClaims(Date accidentDate) {
        return GclEkrookaClaimsMapper.toGclEkrookaClaimsDtoList(gclEkrookaClaimsRepository.getGclEkrookaClaims(accidentDate));
    }
}
