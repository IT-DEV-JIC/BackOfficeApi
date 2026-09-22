package com.jicjo.apis.mapper.crossselling;


import com.jicjo.apis.dto.crossselling.CsIgnoreListDto;
import com.jicjo.apis.model.crossselling.CsIgnoreList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CsIgnoreListMapper {

    public static CsIgnoreListDto toCsIgnoreListDto(CsIgnoreList csIgnoreList){
        return new CsIgnoreListDto(
                csIgnoreList.getIgnoreId(),
                csIgnoreList.getCustomerId(),
                csIgnoreList.getCustomerNo(),
                csIgnoreList.getSourceLob(),
                csIgnoreList.getTargetLob(),
                csIgnoreList.getRuleId(),
                csIgnoreList.getIgnoreReason(),
                csIgnoreList.getCreatedBy(),
                csIgnoreList.getCreatedDate(),
                csIgnoreList.getIsActive()
        );
    }

    public static CsIgnoreList toCsIgnoreList(CsIgnoreListDto csIgnoreListDto){
        return new CsIgnoreList(
                csIgnoreListDto.getIgnoreId(),
                csIgnoreListDto.getCustomerId(),
                csIgnoreListDto.getCustomerNo(),
                csIgnoreListDto.getSourceLob(),
                csIgnoreListDto.getTargetLob(),
                csIgnoreListDto.getRuleId(),
                csIgnoreListDto.getIgnoreReason(),
                csIgnoreListDto.getCreatedBy(),
                csIgnoreListDto.getCreatedDate(),
                csIgnoreListDto.getIsActive()
        );
    }
}
