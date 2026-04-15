package com.jicjo.apis.service.general.impl;

import com.jicjo.apis.dto.general.AccidentHeatmapDto;
import com.jicjo.apis.repository.general.AccidentHeatmapRepository;
import com.jicjo.apis.service.general.AccidentHeatmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.util.Date;
import java.util.List;

@Service
public class AccidentHeatmapServiceImpl implements AccidentHeatmapService {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    AccidentHeatmapRepository accidentHeatmapRepository;

    @Override
    @Transactional
    public List<AccidentHeatmapDto> getAccidentHeatmapDto(Date date) {
        return accidentHeatmapRepository.getAccidentHeatmapDto(date);
    }
}
