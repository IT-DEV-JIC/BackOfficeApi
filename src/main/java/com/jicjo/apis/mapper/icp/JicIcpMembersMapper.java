package com.jicjo.apis.mapper.icp;

import com.jicjo.apis.dto.icp.JicIcpMembersDto;
import com.jicjo.apis.model.icp.JicIcpMembers;

import java.io.Serial;
import java.io.Serializable;

public class JicIcpMembersMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static JicIcpMembersDto toJicIcpMembersDto(JicIcpMembers jicIcpMembers) {
        return new JicIcpMembersDto(
                jicIcpMembers.getId(),
                jicIcpMembers.getJicId(),
                jicIcpMembers.getJicMbrId(),
                jicIcpMembers.getTransactionDate(),
                jicIcpMembers.getMemberRefNo(),
                jicIcpMembers.getMemberCreationRefNo(),
                jicIcpMembers.getMemberStatusCode(),
                jicIcpMembers.getMemberErrorCode(),
                jicIcpMembers.getMemberErrorDesc(),
                jicIcpMembers.getStatusCode(),
                jicIcpMembers.getErrorCode(),
                jicIcpMembers.getErrorDesc(),
                jicIcpMembers.getIcpJson(),
                jicIcpMembers.getJicJson(),
                jicIcpMembers.getAction(),
                jicIcpMembers.getJicConId(),
                jicIcpMembers.getCreatedBy(),
                jicIcpMembers.getCreatedOn(),
                jicIcpMembers.getUpdatedBy(),
                jicIcpMembers.getUpdatedOn()
        );
    }

    public static JicIcpMembers toJicIcpMembers(JicIcpMembersDto jicIcpMembersDto) {
        return new JicIcpMembers(
                jicIcpMembersDto.getId(),
                jicIcpMembersDto.getJicId(),
                jicIcpMembersDto.getJicMbrId(),
                jicIcpMembersDto.getTransactionDate(),
                jicIcpMembersDto.getMemberRefNo(),
                jicIcpMembersDto.getMemberCreationRefNo(),
                jicIcpMembersDto.getMemberStatusCode(),
                jicIcpMembersDto.getMemberErrorCode(),
                jicIcpMembersDto.getMemberErrorDesc(),
                jicIcpMembersDto.getStatusCode(),
                jicIcpMembersDto.getErrorCode(),
                jicIcpMembersDto.getErrorDesc(),
                jicIcpMembersDto.getIcpJson(),
                jicIcpMembersDto.getJicJson(),
                jicIcpMembersDto.getAction(),
                jicIcpMembersDto.getJicConId(),
                jicIcpMembersDto.getCreatedBy(),
                jicIcpMembersDto.getCreatedOn(),
                jicIcpMembersDto.getUpdatedBy(),
                jicIcpMembersDto.getUpdatedOn()
        );
    }
}
