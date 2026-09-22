package com.jicjo.apis.service.crossselling;

import com.jicjo.apis.dto.crossselling.CsIgnoreListDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CsIgnoreListService {
    CsIgnoreListDto addIgnore(CsIgnoreListDto csIgnoreListDto);

    List<CsIgnoreListDto> getAllIgnoreList();

    void removeIgnore(Long ignoreId);
}
