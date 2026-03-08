package com.jicjo.apis.repository.core;

import com.jicjo.apis.model.core.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long>, Serializable {
}
