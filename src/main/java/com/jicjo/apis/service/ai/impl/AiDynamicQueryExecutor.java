
package com.jicjo.apis.service.ai.impl;

import com.jicjo.apis.repository.ai.AiDynamicQueryRepository.ParameterDefinition;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class AiDynamicQueryExecutor {

    private final NamedParameterJdbcTemplate jdbc;

    public AiDynamicQueryExecutor(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Map<String, Object>> execute(
            String approvedSql,
            Map<String, Object> aiParameters,
            List<ParameterDefinition> definitions,
            int maxRows,
            int timeoutSeconds) {

        if (approvedSql == null || approvedSql.isBlank()) {
            throw new IllegalArgumentException("SQL query is empty");
        }

        if (maxRows <= 0 || timeoutSeconds <= 0) {
            throw new IllegalArgumentException(
                    "Invalid query execution limits");
        }

        if (definitions == null) {
            throw new IllegalArgumentException(
                    "Parameter metadata is missing");
        }

        Map<String, Object> supplied =
                aiParameters == null ? Map.of() : aiParameters;

        Map<String, ParameterDefinition> metadata =
                new LinkedHashMap<>();

        for (ParameterDefinition definition : definitions) {

            if (metadata.putIfAbsent(
                    definition.name(), definition) != null) {

                throw new IllegalArgumentException(
                        "Duplicate parameter: " + definition.name());
            }
        }

        if (!metadata.keySet().containsAll(supplied.keySet())) {
            throw new IllegalArgumentException(
                    "AI supplied unknown parameters");
        }

        MapSqlParameterSource binds =
                new MapSqlParameterSource();

        for (ParameterDefinition definition : definitions) {

            String name = definition.name();
            Object value = supplied.get(name);

            if (value == null) {
                value = definition.defaultValue();
            }

            if (definition.required() && value == null) {
                throw new IllegalArgumentException(
                        "Required parameter missing: " + name);
            }

            if (value != null
                    && definition.validationRegex() != null
                    && !definition.validationRegex().isBlank()) {

                if (!Pattern.matches(
                        definition.validationRegex(),
                        value.toString())) {

                    throw new IllegalArgumentException(
                            "Invalid parameter: " + name);
                }
            }

            int jdbcType = resolveJdbcType(definition.type());

            binds.addValue(
                    name,
                    convertValue(value, jdbcType),
                    jdbcType
            );
        }

        return jdbc.execute(approvedSql, binds, ps -> {

            ps.setQueryTimeout(timeoutSeconds);
            ps.setMaxRows(maxRows);

            try (ResultSet rs = ps.executeQuery()) {

                List<Map<String, Object>> rows =
                        new ArrayList<>();

                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                while (rs.next()) {

                    Map<String, Object> row =
                            new LinkedHashMap<>();

                    for (int i = 1; i <= columnCount; i++) {

                        row.put(
                                meta.getColumnLabel(i),
                                rs.getObject(i)
                        );
                    }

                    rows.add(row);
                }

                return rows;
            }
        });
    }

    private int resolveJdbcType(String type) {

        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException(
                    "Parameter type is missing");
        }

        return switch (type.trim().toUpperCase(Locale.ROOT)) {

            case "NUMBER", "NUMERIC", "DECIMAL" ->
                    Types.NUMERIC;

            case "INTEGER", "INT" ->
                    Types.INTEGER;

            case "VARCHAR", "VARCHAR2", "STRING", "TEXT" ->
                    Types.VARCHAR;

            case "DATE" ->
                    Types.DATE;

            case "TIMESTAMP", "DATETIME" ->
                    Types.TIMESTAMP;

            default -> throw new IllegalArgumentException(
                    "Unsupported parameter type: " + type);
        };
    }

    private Object convertValue(Object value, int jdbcType) {

        if (value == null) {
            return null;
        }

        String text = value.toString().trim();

        return switch (jdbcType) {

            case Types.NUMERIC ->
                    new BigDecimal(text);

            case Types.INTEGER ->
                    Integer.valueOf(text);

            case Types.VARCHAR ->
                    text;

            case Types.DATE ->
                    java.sql.Date.valueOf(
                            LocalDate.parse(text));

            case Types.TIMESTAMP ->
                    Timestamp.valueOf(
                            LocalDateTime.parse(
                                    text.replace(" ", "T")));

            default -> throw new IllegalArgumentException(
                    "Unsupported JDBC type: " + jdbcType);
        };
    }
}
