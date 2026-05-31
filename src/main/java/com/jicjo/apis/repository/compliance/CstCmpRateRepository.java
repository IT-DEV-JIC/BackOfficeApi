package com.jicjo.apis.repository.compliance;

import com.jicjo.apis.model.compliance.CstCmpRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface CstCmpRateRepository extends JpaRepository<CstCmpRate,Long>, Serializable {

    @Query("select C from CstCmpRate C where C.cstCmrId = :cstCmrId")
    Optional<CstCmpRate> findCstCmpRateByCstCmrId(Long cstCmrId);

    @Query(value = """
            SELECT  RAT.CST_CMR_ID,
                    RAT.CST_CMP_ID,
                    RAT.CST_CDO_USER,
                    NVL(RAT.CST_CMR_STARS,0) CST_CMR_STARS,
                    RAT.CST_CMR_CREATION_DATE
        
        FROM    CST_CMP_RATE RAT
        WHERE   RAT.CST_CMR_ID = (SELECT  MAX(XRAT.CST_CMR_ID)
                                  FROM    CST_CMP_RATE XRAT
                                  WHERE   XRAT.CST_CMP_ID = :cstCmpId
                                  GROUP   BY NVL(CST_CMR_STARS,0)
                                  HAVING  NVL(CST_CMR_STARS,0) = MAX(CST_CMR_STARS)
                                 )
        """, nativeQuery = true)
    Optional<CstCmpRate> findCstCmpRateByCstCmpId(Long cstCmpId);

    @Query(value = """
            SELECT  RAT.CST_CMR_ID,
                    RAT.CST_CMP_ID,
                    RAT.CST_CDO_USER,
                    NVL(RAT.CST_CMR_STARS,0) CST_CMR_STARS,
                    RAT.CST_CMR_CREATION_DATE
                                                                                   
            FROM    CST_CMP_RATE RAT
            WHERE   RAT.CST_CMR_ID = (SELECT  MAX(XRAT.CST_CMR_ID)
            FROM    CST_CMP_RATE XRAT
            WHERE   XRAT.CST_CMP_ID IN(SELECT CST_CMP_ID FROM CST_CMP_RATE WHERE CST_CMR_ID = :cstCmrId)
            GROUP   BY NVL(CST_CMR_STARS,0)
            HAVING  NVL(CST_CMR_STARS,0) = MAX(CST_CMR_STARS))
        """, nativeQuery = true)
    Optional<CstCmpRate> findCstCmpRateByCstCmrId2(Long cstCmrId);
}
