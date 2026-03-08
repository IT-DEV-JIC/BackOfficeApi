package com.jicjo.apis.service.core;

import com.jicjo.apis.dto.core.ClientsDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface ClientsService extends Serializable {
    ClientsDto getClientsByclntName(String clntName);
}
