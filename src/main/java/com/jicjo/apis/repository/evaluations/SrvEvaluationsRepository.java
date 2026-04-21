package com.jicjo.apis.repository.evaluations;

import com.jicjo.apis.model.evaluations.SrvEvaluations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;


@Repository
public interface SrvEvaluationsRepository  extends JpaRepository<SrvEvaluations,Long>, Serializable {
    @Query("SELECT C FROM SrvEvaluations C WHERE C.srvEvlId = :srvEvlId")
    Optional<SrvEvaluations> findSrvEvaluationById(Long srvEvlId);
}
