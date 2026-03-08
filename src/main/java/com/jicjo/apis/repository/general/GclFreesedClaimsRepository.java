package com.jicjo.apis.repository.general;

import com.jicjo.apis.dto.general.GclFreezedClaimsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface GclFreesedClaimsRepository extends JpaRepository<GclFreezedClaimsDto, Long>, Serializable {
    @Query(value = """
            SELECT  ROWNUM ID,
                    ECLM.KROKA_NO KROKANO,
                    (SELECT CLM.SEGMENT_CODE FROM GCL_CLAIMS CLM WHERE CLM.ID = NTS.GCL_CLM_ID)  SEGMENTCODE,
                    (SELECT TRUNC(CLM.REGISTRATION_DATE) FROM GCL_CLAIMS CLM WHERE CLM.ID = NTS.GCL_CLM_ID) REGISTRATIONDATE
        
            FROM    GCL_KROOKA_JIF_CONTRACTS CONT,
                    GCL_KROOKA_VEHICLE_INFO  INFO,
                    GCL_EKROOKA_CLAIMS ECLM,
                    GCL_CLAIM_NOTICES NTS
        
            WHERE   CONT.GCL_EKC_ID = INFO.GCL_EKC_ID
            AND     ECLM.ID = CONT.GCL_EKC_ID
            AND     NTS.POLICE_REPORT_NO = ECLM.KROKA_NO
            AND     INFO.POLICY_TYPE = 2
            AND     INFO.INSURANCE_COMPANY_ID = 1
            AND     NTS.GCL_CLM_ID IS NOT NULL
            AND     CONT.JIF_POLICY_STATUS_DESC = 'مجمدة'
        """, nativeQuery = true)
    List<GclFreezedClaimsDto> getFreezedLog();
}
