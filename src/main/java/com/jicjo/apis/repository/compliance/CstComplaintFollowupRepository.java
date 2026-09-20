package com.jicjo.apis.repository.compliance;

import com.jicjo.apis.model.compliance.CstComplaintFollowup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface CstComplaintFollowupRepository extends JpaRepository<CstComplaintFollowup,Long>, Serializable {

    @Query("SELECT C FROM CstComplaintFollowup C WHERE C.cstCflId = :cstCflId")
    Optional<CstComplaintFollowup> findCstComplaintFollowupByCstCflId(Long cstCflId);

    @Query("SELECT C FROM CstComplaintFollowup C WHERE C.cstCmpId = :cstCmpId")
    List<CstComplaintFollowup> findCstComplaintFollowupByCstCmpId(Long cstCmpId);

}
