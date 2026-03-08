package com.jicjo.apis.service.core.impl;

import com.jicjo.apis.dto.core.ClientsDto;
import com.jicjo.apis.exception.ResourceNotFoundException;
import com.jicjo.apis.mapper.core.ClientsMapper;
import com.jicjo.apis.repository.core.ClientsRepository;
import com.jicjo.apis.service.core.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;

@Service
public class ClientsServiceImpl implements ClientsService {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private ClientsRepository clientsRepository;

    @Override
    public ClientsDto getClientsByclntName(String clntName) {
        return ClientsMapper.toClientsDto(clientsRepository.findByClntName(clntName)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with name: " + clntName)));
    }
}
