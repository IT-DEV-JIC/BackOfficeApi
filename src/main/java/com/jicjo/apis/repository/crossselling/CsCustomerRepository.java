package com.jicjo.apis.repository.crossselling;

import com.jicjo.apis.dto.crossselling.CsCustomerInfoDto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class CsCustomerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CsCustomerRepository(
            NamedParameterJdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<CsCustomerInfoDto> findByCustomerIds(
            List<Long> customerIds
    ) {

        if (customerIds == null || customerIds.isEmpty()) {
            return Collections.emptyList();
        }

        String sql = """
                SELECT
                    CUSTOMER_ID,
                    CUSTOMER_NO,
                    CUSTOMER_NAME_EN,
                    CUSTOMER_NAME_AR
                FROM VW_CS_CUSTOMERS
                WHERE CUSTOMER_ID IN (:customerIds)
                """;

        MapSqlParameterSource parameters =
                new MapSqlParameterSource();

        parameters.addValue(
                "customerIds",
                customerIds
        );

        return jdbcTemplate.query(
                sql,
                parameters,
                (rs, rowNum) -> new CsCustomerInfoDto(
                        rs.getLong("CUSTOMER_ID"),
                        rs.getString("CUSTOMER_NO"),
                        rs.getString("CUSTOMER_NAME_EN"),
                        rs.getString("CUSTOMER_NAME_AR")
                )
        );
    }

    public CsCustomerInfoDto findByCustomerId(Long customerId) {

        String sql = """
            SELECT
                CUSTOMER_ID,
                CUSTOMER_NO,
                CUSTOMER_NAME_EN,
                CUSTOMER_NAME_AR
            FROM VW_CS_CUSTOMERS
            WHERE CUSTOMER_ID = :customerId
            """;

        MapSqlParameterSource parameters =
                new MapSqlParameterSource()
                        .addValue("customerId", customerId);

        List<CsCustomerInfoDto> result =
                jdbcTemplate.query(
                        sql,
                        parameters,
                        (rs, rowNum) -> new CsCustomerInfoDto(
                                rs.getLong("CUSTOMER_ID"),
                                rs.getString("CUSTOMER_NO"),
                                rs.getString("CUSTOMER_NAME_EN"),
                                rs.getString("CUSTOMER_NAME_AR")
                        )
                );

        return result.isEmpty()
                ? null
                : result.get(0);
    }
}