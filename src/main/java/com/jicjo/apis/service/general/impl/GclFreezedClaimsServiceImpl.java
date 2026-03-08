package com.jicjo.apis.service.general.impl;

import com.jicjo.apis.dto.general.GclFreezedClaimsDto;
import com.jicjo.apis.repository.general.GclFreesedClaimsRepository;
import com.jicjo.apis.service.general.GclFreezedClaimsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;

@Service
public class GclFreezedClaimsServiceImpl implements GclFreezedClaimsService {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private GclFreesedClaimsRepository gclFreesedClaimsRepository;

    @Override
    @Transactional
    public List<GclFreezedClaimsDto> getFreezedLog() {
        return gclFreesedClaimsRepository.getFreezedLog();
    }
}
