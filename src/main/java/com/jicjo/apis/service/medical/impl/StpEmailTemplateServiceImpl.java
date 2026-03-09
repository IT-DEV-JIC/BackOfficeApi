package com.jicjo.apis.service.medical.impl;

import com.jicjo.apis.dto.medical.StpEmailTemplateDto;
import com.jicjo.apis.mapper.medical.StpEmailTemplateMapper;
import com.jicjo.apis.model.medical.StpEmailTemplate;
import com.jicjo.apis.repository.medical.StpEmailTemplateRepository;
import com.jicjo.apis.service.medical.StpEmailTemplateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;


@Service
public class StpEmailTemplateServiceImpl implements StpEmailTemplateService {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private StpEmailTemplateRepository stpEmailTemplateRepository;

    @Override
    @Transactional
    public StpEmailTemplateDto saveStpEmailTemplate(StpEmailTemplateDto stpEmailTemplateDto) {
        StpEmailTemplate stpEmailTemplate = new StpEmailTemplate();
        stpEmailTemplate.setStpEmtId(null);
        stpEmailTemplate = StpEmailTemplateMapper.toEmailTemplate(stpEmailTemplateDto);
        return StpEmailTemplateMapper.toEmailTemplateDto(stpEmailTemplateRepository.save(stpEmailTemplate));
    }

    @Override
    public StpEmailTemplateDto updateStpEmailTemplate(StpEmailTemplateDto stpEmailTemplateDto) {
        StpEmailTemplate stpEmailTemplate = stpEmailTemplateRepository.findById(stpEmailTemplateDto.getStpEmtId()).orElseThrow(() -> new UsernameNotFoundException("Email template not found"));

        stpEmailTemplate.setStpEmtConnect(stpEmailTemplateDto.getStpEmtConnect());
        stpEmailTemplate.setStpEmtTo(stpEmailTemplateDto.getStpEmtTo());
        stpEmailTemplate.setStpEmtCc(stpEmailTemplateDto.getStpEmtCc());
        stpEmailTemplate.setStpEmtBcc(stpEmailTemplateDto.getStpEmtBcc());
        stpEmailTemplate.setStpEmtTitle(stpEmailTemplateDto.getStpEmtTitle());
        stpEmailTemplate.setStpEmtContents(stpEmailTemplateDto.getStpEmtContents());

        return StpEmailTemplateMapper.toEmailTemplateDto(stpEmailTemplateRepository.save(stpEmailTemplate));
    }

    @Override
    @Transactional
    public StpEmailTemplateDto getStpEmailTemplate(Long stpEmtId) {
        return StpEmailTemplateMapper.toEmailTemplateDto(stpEmailTemplateRepository.findByStpEmtId(stpEmtId).orElseThrow(() -> new UsernameNotFoundException("Email template not found")));
    }

    @Override
    @Transactional
    public List<StpEmailTemplateDto> getStpEmailTemplateByParent(Long stpEmtConnectBy) {
        return StpEmailTemplateMapper.toStpEmailTemplateDtoList(stpEmailTemplateRepository.getTemplatesByStpEmtConnect(stpEmtConnectBy));
    }
}
