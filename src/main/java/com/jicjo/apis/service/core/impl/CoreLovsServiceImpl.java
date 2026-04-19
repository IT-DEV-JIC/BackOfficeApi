package com.jicjo.apis.service.core.impl;

import com.jicjo.apis.dto.core.CoreLovs;
import com.jicjo.apis.repository.core.CoreLovsRepository;
import com.jicjo.apis.service.core.CoreLovsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;

@Service
public class CoreLovsServiceImpl implements CoreLovsService {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private CoreLovsRepository coreLovsRepository;

    @Override
    public List<CoreLovs> getCstByclntName(String clntName) {
        return this.coreLovsRepository.findCstByclntName(clntName);
    }

    @Override
    public List<CoreLovs> findBrnByclntName(String clntName) {
        return this.coreLovsRepository.findBrnByclntName(clntName);
    }

    @Override
    public List<CoreLovs> findMpdPolicies() {
        return this.coreLovsRepository.findMpdPolicies();
    }

    @Override
    public List<CoreLovs> findUsersByclntName(String clntName, String userName) {
        return this.coreLovsRepository.findUsersByclntName(clntName, userName);
    }
}
