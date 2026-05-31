package com.jicjo.apis.repository.compliance;

import com.jicjo.apis.dto.compliance.CstComplaintsDashboardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CstComplaintsDashboardRepository extends JpaRepository<CstComplaintsDashboardDto,Long>, Serializable {

    @Query(value = """
        SELECT
            ROWNUM AS "id",
            T.SC_ADESC AS "complaintType",
            S.SC_ADESC AS "statusDesc",
            P.SC_ADESC AS "priorityDesc",
            TRUNC(CST.CST_CMP_STATUS_DATE) AS "cstCmpStatusDate",

            COUNT(1) OVER (
                PARTITION BY TRUNC(CST.CST_CMP_CREATION_DATE), T.SC_ADESC, S.SC_ADESC, P.SC_ADESC
            ) AS "rowCount",

            SUM(1) OVER (
                PARTITION BY TRUNC(CST.CST_CMP_CREATION_DATE), S.SC_ADESC
            ) AS "statusSubtotal",

            SUM(1) OVER (
                PARTITION BY T.SC_ADESC
            ) AS "typeTotal",

            SUM(1) OVER () AS "grandTotal"

        FROM CST_COMPLAINTS CST
        LEFT JOIN SYS_CODES P ON P.SC_TYPE = 100009 AND P.SC_CODE = CST.CST_CMP_PRIORITY
        LEFT JOIN SYS_CODES S ON S.SC_TYPE = 100011 AND S.SC_CODE = CST.CST_CMP_STATUS
        LEFT JOIN SYS_CODES T ON T.SC_TYPE = 100008 AND T.SC_CODE = CST.CST_CMP_TYPE
        WHERE CST.CST_CMP_TYPE IN (1, 2)
          AND TRUNC(CST.CST_CMP_STATUS_DATE) BETWEEN TRUNC(ADD_MONTHS(SYSDATE,-12)) AND TRUNC(SYSDATE)
        """, nativeQuery = true)
    List<CstComplaintsDashboardDto> getCstComplaintsDashboar();
}
