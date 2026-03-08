package com.jicjo.apis.repository.core;

import com.jicjo.apis.model.core.SysCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface SysCodesRepository extends JpaRepository<SysCodes, Long>, Serializable {
    @Query("SELECT S FROM SysCodes S " +
            "WHERE (:scType IS NULL OR S.scType = :scType) " +
            "AND   (:scCode IS NULL OR S.scCode =  :scCode) " +
            "AND   S.scCode != 0 "
    )
    List<SysCodes> getSysCodes(Long scType, Long scCode);
}
