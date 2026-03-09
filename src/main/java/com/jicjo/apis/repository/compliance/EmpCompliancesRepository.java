package com.jicjo.apis.repository.compliance;

import com.jicjo.apis.model.compliance.EmpCompliances;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface EmpCompliancesRepository extends JpaRepository<EmpCompliances,Long>, Serializable {
}
