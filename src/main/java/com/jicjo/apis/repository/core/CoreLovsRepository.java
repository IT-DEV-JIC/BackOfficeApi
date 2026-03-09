package com.jicjo.apis.repository.core;

import com.jicjo.apis.dto.core.CoreLovs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface CoreLovsRepository extends JpaRepository<CoreLovs,Long>, Serializable {

    @Query(value = """

            SELECT ID, NAME, NAME2 FROM FCS_CUSTOMERS
            WHERE ID IN(
                          SELECT  DISTINCT REGEXP_SUBSTR(CST_IDS,'[^,]+', 1, LEVEL)
                          FROM    CLIENTS
                          WHERE   CLNT_NAME = :clntName
                          CONNECT BY REGEXP_SUBSTR(CST_IDS, '[^,]+', 1, LEVEL) IS NOT NULL
                        )
        """, nativeQuery = true)
    List<CoreLovs> findCstByclntName(String clntName);

    @Query(value = """
        SELECT BRN_ID AS ID, BRN_EN_NAME AS NAME, BRN_AR_NAME AS NAME2 FROM BRANCHES WHERE CLNT_NAME = DECODE(:clntName,'demo',CLNT_NAME,:clntName)
        """, nativeQuery = true)
    List<CoreLovs> findBrnByclntName(String clntName);

    @Query(value = """

            SELECT  PST_APW_ID AS ID, PST_APW_AR_DESC AS NAME, PST_APW_EN_DESC AS NAME2
            FROM    PST_APPLICATION_WORDINGS 
            WHERE   (PST_APW_ID = :pstApwId OR :pstApwId IS NULL) 
        """, nativeQuery = true)
    List<CoreLovs> findPstApplicationWordingsById(Long pstApwId);

    @Query(value = """

            SELECT  ID, SEGMENT_CODE AS NAME, SEGMENT_CODE AS NAME2
            FROM    MPD_POLICIES 
            WHERE   CRG_BRN_ID IN (301,303,305)
            AND     DOCUMENT_TYPE IN (1,3)
            AND     TRUNC(EXPIRY_DATE) >= TRUNC(ADD_MONTHS(SYSDATE, -12))
        """, nativeQuery = true)
    List<CoreLovs> findMpdPolicies();
}
