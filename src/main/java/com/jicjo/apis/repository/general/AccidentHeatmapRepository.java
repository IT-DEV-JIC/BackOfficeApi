package com.jicjo.apis.repository.general;

import com.jicjo.apis.dto.general.AccidentHeatmapDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Repository
public interface AccidentHeatmapRepository extends JpaRepository<AccidentHeatmapDto, Long>, Serializable {
    @Query(value = """
                   SELECT  ROWNUM AS ID,
                           EKC.LONGITUDE LONGITUDE,
                           EKC.LATITUDE  LATITUDE
                                      
                   FROM    GCL_EKROOKA_CLAIMS EKC,
                           GCL_KROOKA_JIF_CONTRACTS KJC
                   WHERE   EKC.ID = KJC.GCL_EKC_ID
                   AND     KJC.JIF_COMPANY_ID = 1
                   AND     TRUNC(EKC.ACCIDENT_DATE) = TRUNC(:date)
                   """,
            nativeQuery = true)
    List<AccidentHeatmapDto> getAccidentHeatmapDto(Date date);
}
