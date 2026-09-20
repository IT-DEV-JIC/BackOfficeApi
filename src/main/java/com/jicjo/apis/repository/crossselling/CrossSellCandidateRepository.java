package com.jicjo.apis.repository.crossselling;

import com.jicjo.apis.dto.crossselling.CrossSellCandidateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class CrossSellCandidateRepository implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final JdbcTemplate jdbcTemplate;

    public List<CrossSellCandidateDto> findAllCandidates() {

        String sql = """
            SELECT
                CUSTOMER_ID,
                CUSTOMER_NO,
                CUSTOMER_NAME_EN,
                CUSTOMER_NAME_AR,
                CUSTOMER_TYPE,
                RULE_ID,
                RULE_NAME,
                SOURCE_LOB,
                TARGET_LOB,
                MIN_PREMIUM,
                MIN_POLICY_COUNT,
                PRIORITY,
                POLICY_COUNT,
                TOTAL_GROSS_PREMIUM_LC,
                HAS_SOURCE_LOB,
                HAS_TARGET_LOB
            FROM VW_CS_CROSS_SELL_CANDIDATES
            ORDER BY PRIORITY,
                     TOTAL_GROSS_PREMIUM_LC DESC
        """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    CrossSellCandidateDto dto =
                            new CrossSellCandidateDto();

                    dto.setCustomerId(
                            rs.getLong("CUSTOMER_ID")
                    );

                    dto.setCustomerNo(
                            rs.getString("CUSTOMER_NO")
                    );

                    dto.setCustomerNameEn(
                            rs.getString("CUSTOMER_NAME_EN")
                    );

                    dto.setCustomerNameAr(
                            rs.getString("CUSTOMER_NAME_AR")
                    );

                    dto.setCustomerType(
                            rs.getString("CUSTOMER_TYPE")
                    );

                    dto.setRuleId(
                            rs.getLong("RULE_ID")
                    );

                    dto.setRuleName(
                            rs.getString("RULE_NAME")
                    );

                    dto.setSourceLob(
                            rs.getString("SOURCE_LOB")
                    );

                    dto.setTargetLob(
                            rs.getString("TARGET_LOB")
                    );

                    dto.setMinPremium(
                            rs.getBigDecimal("MIN_PREMIUM")
                    );

                    Object minPolicyCount =
                            rs.getObject("MIN_POLICY_COUNT");

                    dto.setMinPolicyCount(
                            minPolicyCount == null
                                    ? null
                                    : ((Number) minPolicyCount).intValue()
                    );

                    Object priority =
                            rs.getObject("PRIORITY");

                    dto.setPriority(
                            priority == null
                                    ? null
                                    : ((Number) priority).intValue()
                    );

                    Object policyCount =
                            rs.getObject("POLICY_COUNT");

                    dto.setPolicyCount(
                            policyCount == null
                                    ? null
                                    : ((Number) policyCount).intValue()
                    );

                    dto.setTotalGrossPremiumLc(
                            rs.getBigDecimal(
                                    "TOTAL_GROSS_PREMIUM_LC"
                            )
                    );

                    Object hasSource =
                            rs.getObject("HAS_SOURCE_LOB");

                    dto.setHasSourceLob(
                            hasSource == null
                                    ? null
                                    : ((Number) hasSource).intValue()
                    );

                    Object hasTarget =
                            rs.getObject("HAS_TARGET_LOB");

                    dto.setHasTargetLob(
                            hasTarget == null
                                    ? null
                                    : ((Number) hasTarget).intValue()
                    );

                    return dto;
                }
        );
    }

    public CrossSellCandidateDto findCandidate(
            Long customerId,
            Long ruleId
    ) {

        String sql = """
        SELECT
            CUSTOMER_ID,
            CUSTOMER_NO,
            CUSTOMER_NAME_EN,
            CUSTOMER_NAME_AR,
            CUSTOMER_TYPE,
            RULE_ID,
            RULE_NAME,
            SOURCE_LOB,
            TARGET_LOB,
            MIN_PREMIUM,
            MIN_POLICY_COUNT,
            PRIORITY,
            POLICY_COUNT,
            TOTAL_GROSS_PREMIUM_LC,
            HAS_SOURCE_LOB,
            HAS_TARGET_LOB
        FROM VW_CS_CROSS_SELL_CANDIDATES
        WHERE CUSTOMER_ID = ?
          AND RULE_ID = ?
        """;

        List<CrossSellCandidateDto> result = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {

                    CrossSellCandidateDto dto =
                            new CrossSellCandidateDto();

                    dto.setCustomerId(
                            rs.getLong("CUSTOMER_ID")
                    );

                    dto.setCustomerNo(
                            rs.getString("CUSTOMER_NO")
                    );

                    dto.setCustomerNameEn(
                            rs.getString("CUSTOMER_NAME_EN")
                    );

                    dto.setCustomerNameAr(
                            rs.getString("CUSTOMER_NAME_AR")
                    );

                    dto.setCustomerType(
                            rs.getString("CUSTOMER_TYPE")
                    );

                    dto.setRuleId(
                            rs.getLong("RULE_ID")
                    );

                    dto.setRuleName(
                            rs.getString("RULE_NAME")
                    );

                    dto.setSourceLob(
                            rs.getString("SOURCE_LOB")
                    );

                    dto.setTargetLob(
                            rs.getString("TARGET_LOB")
                    );

                    dto.setMinPremium(
                            rs.getBigDecimal("MIN_PREMIUM")
                    );

                    Object minPolicyCount =
                            rs.getObject("MIN_POLICY_COUNT");

                    dto.setMinPolicyCount(
                            minPolicyCount == null
                                    ? null
                                    : ((Number) minPolicyCount).intValue()
                    );

                    Object priority =
                            rs.getObject("PRIORITY");

                    dto.setPriority(
                            priority == null
                                    ? null
                                    : ((Number) priority).intValue()
                    );

                    Object policyCount =
                            rs.getObject("POLICY_COUNT");

                    dto.setPolicyCount(
                            policyCount == null
                                    ? null
                                    : ((Number) policyCount).intValue()
                    );

                    dto.setTotalGrossPremiumLc(
                            rs.getBigDecimal(
                                    "TOTAL_GROSS_PREMIUM_LC"
                            )
                    );

                    Object hasSource =
                            rs.getObject("HAS_SOURCE_LOB");

                    dto.setHasSourceLob(
                            hasSource == null
                                    ? null
                                    : ((Number) hasSource).intValue()
                    );

                    Object hasTarget =
                            rs.getObject("HAS_TARGET_LOB");

                    dto.setHasTargetLob(
                            hasTarget == null
                                    ? null
                                    : ((Number) hasTarget).intValue()
                    );

                    return dto;
                },
                customerId,
                ruleId
        );

        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}
