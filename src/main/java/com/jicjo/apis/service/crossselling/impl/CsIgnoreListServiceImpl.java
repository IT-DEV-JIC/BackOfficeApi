package com.jicjo.apis.service.crossselling.impl;

import com.jicjo.apis.dto.crossselling.CsIgnoreListDto;
import com.jicjo.apis.mapper.crossselling.CsIgnoreListMapper;
import com.jicjo.apis.model.crossselling.CsIgnoreList;
import com.jicjo.apis.repository.crossselling.CsIgnoreListRepository;
import com.jicjo.apis.service.crossselling.CsIgnoreListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsIgnoreListServiceImpl implements CsIgnoreListService, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final CsIgnoreListRepository csIgnoreListRepository;

    @Override
    @Transactional
    public CsIgnoreListDto addIgnore(CsIgnoreListDto dto) {

        CsIgnoreList entity = CsIgnoreListMapper.toCsIgnoreList(dto);

        entity.setIgnoreId(null);
        entity.setCreatedDate(new Date());
        entity.setIsActive(1);

        CsIgnoreList savedEntity = csIgnoreListRepository.save(entity);

        return CsIgnoreListMapper.toCsIgnoreListDto(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CsIgnoreListDto> getAllIgnoreList() {

        return csIgnoreListRepository
                .findAll()
                .stream()
                .map(CsIgnoreListMapper::toCsIgnoreListDto)
                .toList();
    }

    @Override
    @Transactional
    public void removeIgnore(Long ignoreId) {

        CsIgnoreList entity = csIgnoreListRepository
                .findById(ignoreId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Ignore record not found: " + ignoreId
                        )
                );

        entity.setIsActive(0);

        csIgnoreListRepository.save(entity);
    }
}
