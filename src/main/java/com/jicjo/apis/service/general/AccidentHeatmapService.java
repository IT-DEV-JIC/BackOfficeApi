package com.jicjo.apis.service.general;

import com.jicjo.apis.dto.general.AccidentHeatmapDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
public interface AccidentHeatmapService extends Serializable {
    List<AccidentHeatmapDto> getAccidentHeatmapDto(Date date);
}
