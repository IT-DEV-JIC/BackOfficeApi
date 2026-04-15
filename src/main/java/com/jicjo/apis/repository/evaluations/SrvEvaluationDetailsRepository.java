package com.jicjo.apis.repository.evaluations;

import com.jicjo.apis.model.evaluations.SrvEvaluationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


@Repository
public interface SrvEvaluationDetailsRepository extends JpaRepository<SrvEvaluationDetails,Long>, Serializable {

}
