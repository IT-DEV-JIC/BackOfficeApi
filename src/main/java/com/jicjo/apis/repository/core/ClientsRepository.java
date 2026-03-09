package com.jicjo.apis.repository.core;

import com.jicjo.apis.model.core.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Clients,Long>, Serializable {
    @Query("SELECT C FROM Clients C WHERE C.clntName = :clntName")
    Optional<Clients> findByClntName(String clntName);
}
