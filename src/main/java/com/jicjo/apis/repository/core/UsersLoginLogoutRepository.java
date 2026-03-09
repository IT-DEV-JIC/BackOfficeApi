package com.jicjo.apis.repository.core;

import com.jicjo.apis.model.core.UsersLoginLogout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UsersLoginLogoutRepository extends JpaRepository<UsersLoginLogout, Long>, Serializable {
    UsersLoginLogout findByUsrlId(Long usrlId);
}
