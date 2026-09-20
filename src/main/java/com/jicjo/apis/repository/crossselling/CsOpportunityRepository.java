package com.jicjo.apis.repository.crossselling;


import com.jicjo.apis.model.crossselling.CsOpportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Repository
public interface CsOpportunityRepository extends JpaRepository<CsOpportunity,Long>, Serializable {

    @Query(
            value = "SELECT CS_OPPORTUNITY_NO_SEQ.NEXTVAL FROM DUAL",
            nativeQuery = true
    )
    Long getNextOpportunityNo();


    boolean existsByCustomerIdAndTargetLobAndStatusIn(
            Long customerId,
            String targetLob,
            Collection<String> statuses
    );


    List<CsOpportunity>
    findByCustomerIdOrderByCreatedDateDesc(
            Long customerId
    );
}
