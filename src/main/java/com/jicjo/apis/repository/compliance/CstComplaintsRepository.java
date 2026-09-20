package com.jicjo.apis.repository.compliance;

import com.jicjo.apis.model.compliance.CstComplaints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CstComplaintsRepository extends JpaRepository<CstComplaints,Long>, Serializable {

    @Query("SELECT C FROM CstComplaints C " +
            "WHERE (:fromDate IS NULL OR C.cstCmpCreationDate >= :fromDate) AND (:toDate IS NULL OR C.cstCmpCreationDate <= :toDate) " +
            "ORDER BY C.cstCmpId desc ")
    List<CstComplaints> getAllCstComplaints(Date fromDate, Date toDate);

    @Query("SELECT C FROM CstComplaints C WHERE C.cstCmpId = :cstCmpId")
    Optional<CstComplaints> getCstComplaintsByCstCmpId(Long cstCmpId);

    @Query("SELECT C FROM CstComplaints C WHERE C.cstCmpNumber = :cstCmpNumber")
    Optional<CstComplaints> getCstComplaintsByCstCmpNumber(String cstCmpNumber);
}
