package com.jicjo.apis.repository.core;

import com.jicjo.apis.dto.core.CoreLovs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface CoreLovsRepository extends JpaRepository<CoreLovs,Long>, Serializable {

    @Query(value = """

            SELECT ID, '' AS VID, NAME, NAME2 FROM FCS_CUSTOMERS
            WHERE ID IN(
                          SELECT  DISTINCT REGEXP_SUBSTR(CST_IDS,'[^,]+', 1, LEVEL)
                          FROM    CLIENTS
                          WHERE   CLNT_NAME = :clntName
                          CONNECT BY REGEXP_SUBSTR(CST_IDS, '[^,]+', 1, LEVEL) IS NOT NULL
                        )
        """, nativeQuery = true)
    List<CoreLovs> findCstByclntName(String clntName);

    @Query(value = """
        SELECT BRN_ID AS ID, '' AS VID, BRN_EN_NAME AS NAME, BRN_AR_NAME AS NAME2 FROM BRANCHES WHERE CLNT_NAME = DECODE(:clntName,'demo',CLNT_NAME,:clntName)
        """, nativeQuery = true)
    List<CoreLovs> findBrnByclntName(String clntName);

    @Query(value = """

            SELECT  ID, '' AS VID, SEGMENT_CODE AS NAME, SEGMENT_CODE AS NAME2
            FROM    MPD_POLICIES 
            WHERE   CRG_BRN_ID IN (301,303,305)
            AND     DOCUMENT_TYPE IN (1,3)
            AND     TRUNC(EXPIRY_DATE) >= TRUNC(ADD_MONTHS(SYSDATE, -12))
        """, nativeQuery = true)
    List<CoreLovs> findMpdPolicies();

    @Query(value = """
            SELECT ROWNUM AS ID,
              U.USR_NAME AS VID,
              U.USR_EN_FULL_NAME AS NAME,
              U.USR_AR_FULL_NAME AS NAME2
            FROM USERS U
            WHERE U.CLNT_NAME = DECODE(:clntName, 'demo', U.CLNT_NAME, :clntName)
         AND (
               EXISTS (
                   SELECT 1
                   FROM GROUP_MEMBERS GM_ADMIN
                   WHERE GM_ADMIN.GRBMEM_MEMBER = :userName
                     AND GM_ADMIN.GRB_ID = 1
               )
               OR EXISTS (
                   SELECT 1
                   FROM GROUP_MEMBERS GM_U
                   WHERE GM_U.GRBMEM_MEMBER = U.USR_NAME
                     AND GM_U.GRB_ID IN (
                           SELECT GM_ME.GRB_ID
                           FROM GROUP_MEMBERS GM_ME
                           WHERE GM_ME.GRBMEM_MEMBER = :userName
                     )
               )
         ) 
       """, nativeQuery = true)
    List<CoreLovs> findUsersByclntName(String clntName, String userName);
}
