package com.jicjo.apis.repository.compliance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serial;
import java.io.Serializable;

@Repository
public class CodeSegmentationRepository implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CodeSegmentationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getCstComplaintNumber(Long cstCmpType) {

        String sql = """
                SELECT GET_CST_COMPLAINT_NUMBER(?)
                FROM DUAL
                """;

        return jdbcTemplate.queryForObject(
                sql,
                String.class,
                cstCmpType
        );
    }
}
