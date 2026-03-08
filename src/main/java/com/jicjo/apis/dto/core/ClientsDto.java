package com.jicjo.apis.dto.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientsDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String clntName;
    private String clntEnClientName;
    private String clntArClientName;
    private String clntClientMobile;
    private String clntClientEmail;
    private String clntEnAddress;
    private String clntArAddress;
    private String clntEnBeneficiaryName;
    private String clntArBeneficiaryName;
    private String clntBeneficiaryMobile;
    private String clntBeneficiaryEmail;
    private Long clntActive;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date clntActiveFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date clntActiveTo;
    private BigDecimal clntSubscriptionAmount;
    private Long clntPaymentStatus;
    private String clntCreatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date clntCreatedOn;
    private String clntUpdatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date clntUpdatedOn;
    private String clntTaxNo;
    private String clntLogo;
    private String cstIds;
    private String clntAttachmentTemplate;
}
