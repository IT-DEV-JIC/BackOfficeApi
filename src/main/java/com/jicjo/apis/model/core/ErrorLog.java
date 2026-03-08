package com.jicjo.apis.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ERROR_LOG", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class ErrorLog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "ERROR_LOG_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "ID")
    private Long id;
    @Column(name = "EXCEPTION_TYPE")
    private String exceptionType;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "STACK_TRACE")
    private String stackTrace;
    @Column(name = "PATH")
    private String path;
    @Column(name = "LOCAL_DATE_TIME")
    private Date timestamp;
}
