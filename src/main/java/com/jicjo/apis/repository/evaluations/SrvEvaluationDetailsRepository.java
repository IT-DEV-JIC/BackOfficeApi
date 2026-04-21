package com.jicjo.apis.repository.evaluations;

import com.jicjo.apis.model.evaluations.SrvEvaluationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;


@Repository
public interface SrvEvaluationDetailsRepository extends JpaRepository<SrvEvaluationDetails,Long>, Serializable {
    @Query("SELECT C FROM SrvEvaluationDetails C WHERE C.srvEvdId = :srvEvdId")
    Optional<SrvEvaluationDetails> findSrvEvaluationDetailsById(Long srvEvdId);
}


