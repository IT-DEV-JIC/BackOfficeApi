
package com.jicjo.apis.repository.ai;

import com.jicjo.apis.service.ai.AiInquiryIntentService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AiInquiryRepository {

    private final JdbcTemplate jdbcTemplate;

    public AiInquiryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AiQueryDefinition> findApprovedQueries() {

        String sql = """
                SELECT
                    Q.ID,
                    Q.QUERY_CODE,
                    Q.QUERY_NAME,
                    Q.QUERY_NAME_AR,
                    Q.DESCRIPTION,
                    Q.DESCRIPTION_AR,
                    K.KEYWORD,
                    K.LANGUAGE_CODE,
                    K.WEIGHT
                FROM JIC_AI_QUERIES Q
                JOIN JIC_AI_QUERY_KEYWORDS K
                    ON K.QUERY_ID = Q.ID
                WHERE Q.STATUS IN ('DRAFT', 'APPROVED')
                ORDER BY K.WEIGHT DESC
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new AiQueryDefinition(
                        rs.getLong("ID"),
                        rs.getString("QUERY_CODE"),
                        rs.getString("QUERY_NAME"),
                        rs.getString("QUERY_NAME_AR"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("DESCRIPTION_AR"),
                        rs.getString("KEYWORD"),
                        rs.getString("LANGUAGE_CODE"),
                        rs.getInt("WEIGHT")
                )
        );
    }

    public record AiQueryDefinition(
            Long id,
            String queryCode,
            String queryName,
            String queryNameAr,
            String description,
            String descriptionAr,
            String keyword,
            String languageCode,
            Integer weight
    ) {}


    public List<AiInquiryIntentService.QueryCandidate>
    findApprovedCandidates() {

        var rows = findApprovedQueries();

        Map<String, List<AiQueryDefinition>> grouped =
                rows.stream().collect(
                        java.util.stream.Collectors.groupingBy(
                                AiQueryDefinition::queryCode,
                                LinkedHashMap::new,
                                java.util.stream.Collectors.toList()
                        )
                );

        List<AiInquiryIntentService.QueryCandidate> result =
                new ArrayList<>();

        grouped.forEach((code, items) -> {
            var first = items.get(0);

            result.add(
                    new AiInquiryIntentService.QueryCandidate(
                            code,
                            first.queryNameAr() != null
                                    ? first.queryNameAr()
                                    : first.queryName(),
                            first.descriptionAr() != null
                                    ? first.descriptionAr()
                                    : first.description(),
                            items.stream()
                                    .map(AiQueryDefinition::keyword)
                                    .toList()
                    )
            );
        });

        return result;
    }

}
