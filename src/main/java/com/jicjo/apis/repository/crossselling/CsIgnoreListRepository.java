package com.jicjo.apis.repository.crossselling;


import com.jicjo.apis.model.crossselling.CsIgnoreList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CsIgnoreListRepository extends JpaRepository<CsIgnoreList,Long>, Serializable {
}
