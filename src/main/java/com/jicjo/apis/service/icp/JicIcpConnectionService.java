package com.jicjo.apis.service.icp;

import com.jicjo.apis.dto.icp.JicIcpConnectionDto;
import com.jicjo.apis.dto.icp.JicIcpMembersDto;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface JicIcpConnectionService extends Serializable {
    JicIcpConnectionDto addJicIcpConnection(JicIcpConnectionDto jicIcpConnectionDto);
    JicIcpMembersDto addJicIcpMembers(JicIcpMembersDto jicIcpMembersDto);
}
