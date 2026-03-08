package com.jicjo.apis.repository.icp;

import com.jicjo.apis.model.icp.JicIcpConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface JicIcpConnectionRepository extends JpaRepository<JicIcpConnection,Long>, Serializable {

    @Query("SELECT J FROM JicIcpConnection J WHERE J.jicId = :jicId")
    List<JicIcpConnection> findByJicId(Long jicId);

    @Query("SELECT J FROM JicIcpConnection J WHERE J.jicId = :jicId AND J.statusCode = 1")
    Optional<JicIcpConnection> findPostedByJicId(Long jicId);
}
