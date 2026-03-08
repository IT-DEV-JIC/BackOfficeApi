package com.jicjo.apis.repository.core;

import com.jicjo.apis.model.core.ApplicationScreens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ApplicationScreensRepository extends JpaRepository<ApplicationScreens,Long>, Serializable {
    @Query(value = """
        SELECT * FROM APPLICATION_SCREENS
        WHERE SCR_ID IN (
                    SELECT SCR_ID FROM GROUP_SCREENS
                    WHERE GRB_ID IN (
                        SELECT GRB_ID FROM GROUP_MEMBERS
                        WHERE GRBMEM_MEMBER = :userName AND CLNT_NAME = :clntName
                    )
                  )
        AND APP_ID = :appId
        """, nativeQuery = true)
    List<ApplicationScreens> getApplicationScreensForMemberAndClient(
            @Param("userName") String userName,
            @Param("clntName") String clntName,
            @Param("appId") Long appId
    );
}
