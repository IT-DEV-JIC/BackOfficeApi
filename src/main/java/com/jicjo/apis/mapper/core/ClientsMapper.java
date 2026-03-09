package com.jicjo.apis.mapper.core;

import com.jicjo.apis.dto.core.ClientsDto;
import com.jicjo.apis.model.core.Clients;

import java.io.Serial;
import java.io.Serializable;

public class ClientsMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static ClientsDto toClientsDto(Clients clients) {
        return new ClientsDto(
                clients.getClntName(),
                clients.getClntEnClientName(),
                clients.getClntArClientName(),
                clients.getClntClientMobile(),
                clients.getClntClientEmail(),
                clients.getClntEnAddress(),
                clients.getClntArAddress(),
                clients.getClntEnBeneficiaryName(),
                clients.getClntArBeneficiaryName(),
                clients.getClntBeneficiaryMobile(),
                clients.getClntBeneficiaryEmail(),
                clients.getClntActive(),
                clients.getClntActiveFrom(),
                clients.getClntActiveTo(),
                clients.getClntSubscriptionAmount(),
                clients.getClntPaymentStatus(),
                clients.getClntCreatedBy(),
                clients.getClntCreatedOn(),
                clients.getClntUpdatedBy(),
                clients.getClntUpdatedOn(),
                clients.getClntTaxNo(),
                clients.getClntLogo(),
                clients.getCstIds(),
                clients.getClntAttachmentTemplate()
                );
    }

    public static Clients toClients(ClientsDto clientsDto) {
        return new Clients(
                clientsDto.getClntName(),
                clientsDto.getClntEnClientName(),
                clientsDto.getClntArClientName(),
                clientsDto.getClntClientMobile(),
                clientsDto.getClntClientEmail(),
                clientsDto.getClntEnAddress(),
                clientsDto.getClntArAddress(),
                clientsDto.getClntEnBeneficiaryName(),
                clientsDto.getClntArBeneficiaryName(),
                clientsDto.getClntBeneficiaryMobile(),
                clientsDto.getClntBeneficiaryEmail(),
                clientsDto.getClntActive(),
                clientsDto.getClntActiveFrom(),
                clientsDto.getClntActiveTo(),
                clientsDto.getClntSubscriptionAmount(),
                clientsDto.getClntPaymentStatus(),
                clientsDto.getClntCreatedBy(),
                clientsDto.getClntCreatedOn(),
                clientsDto.getClntUpdatedBy(),
                clientsDto.getClntUpdatedOn(),
                clientsDto.getClntTaxNo(),
                clientsDto.getClntLogo(),
                clientsDto.getCstIds(),
                clientsDto.getClntAttachmentTemplate()
        );
    }
}
