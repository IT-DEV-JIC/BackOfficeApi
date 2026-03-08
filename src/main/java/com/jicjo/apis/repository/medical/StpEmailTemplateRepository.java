package com.jicjo.apis.repository.medical;


import com.jicjo.apis.model.medical.StpEmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface StpEmailTemplateRepository extends JpaRepository<StpEmailTemplate,Long>, Serializable {

    @Query("SELECT T FROM StpEmailTemplate T WHERE T.stpEmtId = :stpEmtId")
    Optional<StpEmailTemplate> findByStpEmtId(Long stpEmtId);

    @Query("SELECT T FROM StpEmailTemplate T WHERE T.stpEmtConnect = :stpEmtConnect")
    List<StpEmailTemplate> getTemplatesByStpEmtConnect(Long stpEmtConnect);
}
