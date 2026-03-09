package com.jicjo.apis.repository.core;

import com.jicjo.apis.model.core.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog,Long>, Serializable {

}
