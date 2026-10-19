
package com.jicjo.apis.repository.ai;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AiDynamicQueryRepository {

    private final JdbcTemplate jdbc;

    public AiDynamicQueryRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public record QueryDefinition(
            Long id,
            String queryCode,
            String sql,
            String responseHint,
            int maxRows,
            int timeoutSeconds
    ) {}

    public record ParameterDefinition(
            String name,
            String type,
            boolean required,
            String defaultValue,
            String validationRegex
    ) {}

    public Optional<QueryDefinition> findApproved(String queryCode) {

        String sql = """
                SELECT
                    ID,
                    QUERY_CODE,
                    SQL_QUERY,
                    RESPONSE_HINT,
                    MAX_ROWS,
                    TIMEOUT_SECONDS
                FROM JIC_AI_QUERIES
                WHERE QUERY_CODE = ?
                  AND STATUS = 'APPROVED'
                """;

        List<QueryDefinition> results = jdbc.query(
                sql,
                (rs, rowNum) -> new QueryDefinition(
                        rs.getLong("ID"),
                        rs.getString("QUERY_CODE"),
                        rs.getString("SQL_QUERY"),
                        rs.getString("RESPONSE_HINT"),
                        rs.getInt("MAX_ROWS"),
                        rs.getInt("TIMEOUT_SECONDS")
                ),
                queryCode
        );

        return results.stream().findFirst();
    }

    public List<ParameterDefinition> findParameters(Long queryId) {

        String sql = """
                SELECT
                    PARAM_NAME,
                    PARAM_TYPE,
                    IS_REQUIRED,
                    DEFAULT_VALUE,
                    VALIDATION_REGEX
                FROM JIC_AI_QUERY_PARAMS
                WHERE QUERY_ID = ?
                ORDER BY DISPLAY_ORDER
                """;

        return jdbc.query(
                sql,
                (rs, rowNum) -> new ParameterDefinition(
                        rs.getString("PARAM_NAME"),
                        rs.getString("PARAM_TYPE"),
                        rs.getInt("IS_REQUIRED") == 1,
                        rs.getString("DEFAULT_VALUE"),
                        rs.getString("VALIDATION_REGEX")
                ),
                queryId
        );
    }

    public List<String> findPermissions(Long queryId) {

        return jdbc.queryForList(
                """
                SELECT PERMISSION_CODE
                FROM JIC_AI_QUERY_PERMISSIONS
                WHERE QUERY_ID = ?
                """,
                String.class,
                queryId
        );
    }
}
