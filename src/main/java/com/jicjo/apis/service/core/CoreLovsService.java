package com.jicjo.apis.service.core;

import com.jicjo.apis.dto.core.CoreLovs;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public interface CoreLovsService extends Serializable {
    List<CoreLovs> getCstByclntName(String clntName);
    List<CoreLovs> findBrnByclntName(String clntName);
    List<CoreLovs> findPstApplicationWordingsById(Long pstApwId);
    List<CoreLovs> findMpdPolicies();
}
