package com.jicjo.apis.dto.crossselling;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CsCustomerInfoDto {
    private Long customerId;
    private String customerNo;
    private String customerNameEn;
    private String customerNameAr;
}
