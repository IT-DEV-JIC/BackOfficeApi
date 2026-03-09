package com.jicjo.apis.repository.icp;

import com.jicjo.apis.dto.icp.JicIcpMembersDto;
import com.jicjo.apis.model.icp.JicIcpMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface JicIcpMembersRepository extends JpaRepository<JicIcpMembers,Long>, Serializable {

    @Query("SELECT J FROM JicIcpMembers J WHERE J.jicId = :jicId")
    List<JicIcpMembersDto> findByJicId(Long jicId);
}
