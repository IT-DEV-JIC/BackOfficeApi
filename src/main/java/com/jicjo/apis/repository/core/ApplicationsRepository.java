package com.jicjo.apis.repository.core;

import com.jicjo.apis.model.core.Applications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationsRepository extends JpaRepository<Applications,Long>, Serializable {
    @Query(value = "SELECT A FROM Applications A WHERE A.appId = :appId")
    Optional<Applications> findById(Long appId);

    @Query(value = """
        SELECT * FROM APPLICATIONS 
        WHERE APP_ID IN (
            SELECT APP_ID FROM GROUP_SCREENS 
            WHERE GRB_ID IN (
                SELECT GRB_ID FROM GROUP_MEMBERS 
                WHERE GRBMEM_MEMBER = :userName AND CLNT_NAME = :clntName
            )
        )
        """, nativeQuery = true)
    Optional<List<Applications>> getApplicationsForMemberAndClient(
            @Param("userName") String userName,
            @Param("clntName") String clntName
    );
}
