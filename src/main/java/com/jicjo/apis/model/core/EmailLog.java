package com.jicjo.apis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "EMAIL_LOG", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID"})})
public class EmailLog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "EMAIL_LOG_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @JsonIgnore
    private Long id;
    @Column(name = "EMAIL_SENDER")
    private String emailSender;
    @Column(name = "EMAIL_RECEVER")
    private String emailReceiver;
    @Column(name = "EMAIL_CC")
    private String emailCc;
    @Column(name = "EMAIL_BCC")
    private String emailBcc;
    @Column(name = "EMAIL_SUBJECT")
    private String emailSubject;
    @Column(name = "EMAIL_BODY")
    private String emailBody;
    @JsonIgnore
    @Column(name = "CREATION")
    private Date creationDate;
}
