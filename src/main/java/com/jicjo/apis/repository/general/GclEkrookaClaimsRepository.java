package com.jicjo.apis.repository.general;

import com.jicjo.apis.model.general.GclEkrookaClaims;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Service
public interface GclEkrookaClaimsRepository extends JpaRepository<GclEkrookaClaims, Long>, Serializable {

    @Query(value = "SELECT * FROM GCL_EKROOKA_CLAIMS WHERE TRUNC(ACCIDENT_DATE) = TRUNC(:accidentDate)", nativeQuery = true)
    List<GclEkrookaClaims> getGclEkrookaClaims(Date accidentDate);
}
