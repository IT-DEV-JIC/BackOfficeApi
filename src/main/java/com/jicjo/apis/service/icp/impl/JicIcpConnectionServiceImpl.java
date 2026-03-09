package com.jicjo.apis.service.icp.impl;

import com.jicjo.apis.dto.icp.JicIcpConnectionDto;
import com.jicjo.apis.dto.icp.JicIcpMembersDto;
import com.jicjo.apis.mapper.icp.JicIcpConnectionMapper;
import com.jicjo.apis.mapper.icp.JicIcpMembersMapper;
import com.jicjo.apis.model.icp.JicIcpConnection;
import com.jicjo.apis.model.icp.JicIcpMembers;
import com.jicjo.apis.repository.icp.JicIcpConnectionRepository;
import com.jicjo.apis.repository.icp.JicIcpMembersRepository;
import com.jicjo.apis.service.icp.JicIcpConnectionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;

@Service
public class JicIcpConnectionServiceImpl implements JicIcpConnectionService {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private JicIcpConnectionRepository jicIcpConnectionRepository;

    @Autowired
    private JicIcpMembersRepository jicIcpMembersRepository;

    @Override
    @Transactional
    public JicIcpConnectionDto addJicIcpConnection(JicIcpConnectionDto jicIcpConnectionDto) {
        JicIcpConnection jicIcpConnection = JicIcpConnectionMapper.toJicIcpConnection(jicIcpConnectionDto);
        jicIcpConnection.setId(null);
        return JicIcpConnectionMapper.toJicIcpConnectionDto(jicIcpConnectionRepository.save(jicIcpConnection));
    }

    @Override
    @Transactional
    public JicIcpMembersDto addJicIcpMembers(JicIcpMembersDto jicIcpMembersDto) {
        JicIcpConnection jicIcpConnection = jicIcpConnectionRepository.findPostedByJicId(jicIcpMembersDto.getJicId()).orElseThrow(() -> new RuntimeException("Icp Connection not found"));
        jicIcpMembersDto.setJicConId(jicIcpConnection.getId());
        JicIcpMembers jicIcpMembers = JicIcpMembersMapper.toJicIcpMembers(jicIcpMembersDto);
        jicIcpMembers.setId(null);
        return JicIcpMembersMapper.toJicIcpMembersDto(jicIcpMembersRepository.save(jicIcpMembers));
    }
}
