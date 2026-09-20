package com.jicjo.apis.repository.crossselling;

import com.jicjo.apis.model.crossselling.CsOpportunityFollowup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CsOpportunityFollowupReopsitory extends JpaRepository<CsOpportunityFollowup,Long>, Serializable {

    @Query(
            value = "SELECT CS_OPPORTUNITY_FOLLOWUPS_SEQ.NEXTVAL FROM DUAL",
            nativeQuery = true
    )
    Long getNextId();


    List<CsOpportunityFollowup>
    findByOpportunityIdOrderByFollowupDateDesc(
            Long opportunityId
    );
}
