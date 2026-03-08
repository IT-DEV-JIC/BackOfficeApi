package com.jicjo.apis.mapper.icp;

import com.jicjo.apis.dto.icp.JicIcpConnectionDto;
import com.jicjo.apis.model.icp.JicIcpConnection;

import java.io.Serial;
import java.io.Serializable;

public class JicIcpConnectionMapper  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static JicIcpConnectionDto toJicIcpConnectionDto(JicIcpConnection jicIcpConnection){
        return new JicIcpConnectionDto(
                jicIcpConnection.getId(),
                jicIcpConnection.getJicId(),
                jicIcpConnection.getPolicyCreationRefNo(),
                jicIcpConnection.getIcRequestRefNumber(),
                jicIcpConnection.getStatusCode(),
                jicIcpConnection.getErrorCode(),
                jicIcpConnection.getErrorDesc(),
                jicIcpConnection.getJicJson(),
                jicIcpConnection.getIcpJson(),
                jicIcpConnection.getTransactionDate(),
                jicIcpConnection.getCreatedBy(),
                jicIcpConnection.getCreatedOn(),
                jicIcpConnection.getUpdatedBy(),
                jicIcpConnection.getUpdatedOn()
        );
    }

    public static JicIcpConnection toJicIcpConnection(JicIcpConnectionDto jicIcpConnectionDto){
        return new JicIcpConnection(
                jicIcpConnectionDto.getId(),
                jicIcpConnectionDto.getJicId(),
                jicIcpConnectionDto.getPolicyCreationRefNo(),
                jicIcpConnectionDto.getIcRequestRefNumber(),
                jicIcpConnectionDto.getStatusCode(),
                jicIcpConnectionDto.getErrorCode(),
                jicIcpConnectionDto.getErrorDesc(),
                jicIcpConnectionDto.getJicJson(),
                jicIcpConnectionDto.getIcpJson(),
                jicIcpConnectionDto.getTransactionDate(),
                jicIcpConnectionDto.getCreatedBy(),
                jicIcpConnectionDto.getCreatedOn(),
                jicIcpConnectionDto.getUpdatedBy(),
                jicIcpConnectionDto.getUpdatedOn()
        );
    }
}
