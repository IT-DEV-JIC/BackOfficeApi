package com.jicjo.apis.repository.general;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jicjo.apis.dto.general.*;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class TahaRepository implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ExpectedRecoveryLastPaymentDto> getExpectedRecoveryLastPayment(
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
            Date fromDate,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
            Date toDate
    ) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("TAHA_PORTAL")
                .withProcedureName("EXPECTED_RECOVERY_LAST_PAYMENT")
                .declareParameters(
                        new SqlParameter("P_FROM_DATE", Types.DATE),
                        new SqlParameter("P_TO_DATE", Types.DATE),
                        new SqlOutParameter("P_REF_CURSOR", OracleTypes.CURSOR, new ColumnMapRowMapper())
                );

        Map<String, Object> result = jdbcCall.execute(
                new MapSqlParameterSource()
                        .addValue("P_FROM_DATE", fromDate)
                        .addValue("P_TO_DATE", toDate)
        );

        //return (List<Map<String, Object>>) result.get("P_REF_CURSOR");
        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("P_REF_CURSOR");

        return rows.stream().map(row -> {
            ExpectedRecoveryLastPaymentDto dto = new ExpectedRecoveryLastPaymentDto();

            // Convert everything to String safely
            dto.setSEGMENT_CODE(row.get("SEGMENT_CODE") != null ? row.get("SEGMENT_CODE").toString() : null);
            dto.setOUR_POLICY_NO(row.get("OUR_POLICY_NO") != null ? row.get("OUR_POLICY_NO").toString() : null);
            dto.setINSURANCE_CO(row.get("INSURANCE_CO") != null ? row.get("INSURANCE_CO").toString() : null);
            dto.setINSURED_NUMBER(row.get("INSURED_NUMBER") != null ? row.get("INSURED_NUMBER").toString() : null);
            dto.setINSURED(row.get("INSURED") != null ? row.get("INSURED").toString() : null);
            dto.setPLATE_NO(row.get("PLATE_NO") != null ? row.get("PLATE_NO").toString() : null);
            dto.setMT_CHASSIS_NO(row.get("MT_CHASSIS_NO") != null ? row.get("MT_CHASSIS_NO").toString() : null);
            dto.setTP_PLATE_NO(row.get("TP_PLATE_NO") != null ? row.get("TP_PLATE_NO").toString() : null);
            dto.setPOLICY_NO(row.get("POLICY_NO") != null ? row.get("POLICY_NO").toString() : null);
            dto.setRECOVERY(row.get("RECOVERY") != null ? row.get("RECOVERY").toString() : null);
            dto.setDAMAGE_TYPE(row.get("DAMAGE_TYPE") != null ? row.get("DAMAGE_TYPE").toString() : null);
            dto.setCURRUNCY(row.get("CURRUNCY") != null ? row.get("CURRUNCY").toString() : null);
            dto.setCLASS_NAME(row.get("CLASS_NAME") != null ? row.get("CLASS_NAME").toString() : null);
            dto.setPOLICY_TYPE(row.get("POLICY_TYPE") != null ? row.get("POLICY_TYPE").toString() : null);
            dto.setBRANCH(row.get("BRANCH") != null ? row.get("BRANCH").toString() : null);
            dto.setCOURT_CASE(row.get("COURT_CASE") != null ? row.get("COURT_CASE").toString() : null);
            dto.setRESPONSIPILITY_TYPE(row.get("RESPONSIPILITY_TYPE") != null ? row.get("RESPONSIPILITY_TYPE").toString() : null);
            dto.setTRANS_DATE_REC(row.get("TRANS_DATE_REC") != null ? row.get("TRANS_DATE_REC").toString() : null);
            dto.setAS_OF_DATE(row.get("AS_OF_DATE") != null ? row.get("AS_OF_DATE").toString() : null);
            dto.setAS_OF_DATE(row.get("TRANS_DATE_PAYMENT") != null ? row.get("TRANS_DATE_PAYMENT").toString() : null);
            dto.setAS_OF_DATE(row.get("LOSS_DATE") != null ? row.get("LOSS_DATE").toString() : null);

            return dto;
        }).toList();
    }

    public List<MotorComOsRecoveryLastRec> getMotorComOsRecoveryLastRec(
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
            Date fromDate,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
            Date toDate
    ) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("TAHA_PORTAL")
                .withProcedureName("MOTOR_COM_OS_RECOVERY_LAST_REC")
                .declareParameters(
                        new SqlParameter("P_FROM_DATE", Types.DATE),
                        new SqlParameter("P_TO_DATE", Types.DATE),
                        new SqlOutParameter("P_REF_CURSOR", OracleTypes.CURSOR, new ColumnMapRowMapper())
                );

        Map<String, Object> result = jdbcCall.execute(
                new MapSqlParameterSource()
                        .addValue("P_FROM_DATE", fromDate)
                        .addValue("P_TO_DATE", toDate)
        );

        //return (List<Map<String, Object>>) result.get("P_REF_CURSOR");
        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("P_REF_CURSOR");

        return rows.stream().map(row -> {
            MotorComOsRecoveryLastRec dto = new MotorComOsRecoveryLastRec();

            // Convert everything to String safely
            dto.setNOTIFICATION_DATE(row.get("NOTIFICATION_DATE") != null ? row.get("NOTIFICATION_DATE").toString() : null);
            dto.setGST_PLT_CODE(row.get("GST_PLT_CODE") != null ? row.get("GST_PLT_CODE").toString() : null);
            dto.setGST_CLS_ID(row.get("GST_CLS_ID") != null ? row.get("GST_CLS_ID").toString() : null);
            dto.setCRG_BRN_ID(row.get("CRG_BRN_ID") != null ? row.get("CRG_BRN_ID").toString() : null);
            dto.setCRG_CUR_CODE(row.get("CRG_CUR_CODE") != null ? row.get("CRG_CUR_CODE").toString() : null);
            dto.setBRANCH(row.get("BRANCH") != null ? row.get("BRANCH").toString() : null);
            dto.setINSURANCE_CLASS(row.get("INSURANCE_CLASS") != null ? row.get("INSURANCE_CLASS").toString() : null);
            dto.setPOLICY_TYPE(row.get("POLICY_TYPE") != null ? row.get("POLICY_TYPE").toString() : null);
            dto.setCURRENCY(row.get("CURRENCY") != null ? row.get("CURRENCY").toString() : null);
            dto.setREGISTRATION_DATE(row.get("REGISTRATION_DATE") != null ? row.get("REGISTRATION_DATE").toString() : null);
            dto.setBENEFICIARY_NUMBER(row.get("BENEFICIARY_NUMBER") != null ? row.get("BENEFICIARY_NUMBER").toString() : null);
            dto.setBENEFICIARY_NAME(row.get("BENEFICIARY_NAME") != null ? row.get("BENEFICIARY_NAME").toString() : null);
            dto.setCAUSE_OF_LOSS(row.get("CAUSE_OF_LOSS") != null ? row.get("CAUSE_OF_LOSS").toString() : null);
            dto.setDAMAGE_TYPE(row.get("DAMAGE_TYPE") != null ? row.get("DAMAGE_TYPE").toString() : null);
            dto.setPLATE_NO(row.get("PLATE_NO") != null ? row.get("PLATE_NO").toString() : null);
            dto.setMT_CHASSIS_NO(row.get("MT_CHASSIS_NO") != null ? row.get("MT_CHASSIS_NO").toString() : null);
            dto.setINSURED_NUMBER(row.get("INSURED_NUMBER") != null ? row.get("INSURED_NUMBER").toString() : null);
            dto.setINSURED_NAME(row.get("INSURED_NAME") != null ? row.get("INSURED_NAME").toString() : null);
            dto.setPOLICY_NO(row.get("POLICY_NO") != null ? row.get("POLICY_NO").toString() : null);
            dto.setCLAIM_NO(row.get("CLAIM_NO") != null ? row.get("CLAIM_NO").toString() : null);
            dto.setLOSS_DATE(row.get("LOSS_DATE") != null ? row.get("LOSS_DATE").toString() : null);
            dto.setPAYMENT_AMOUNT(row.get("PAYMENT_AMOUNT") != null ? row.get("PAYMENT_AMOUNT").toString() : null);
            dto.setOS_AMOUNT(row.get("OS_AMOUNT") != null ? row.get("OS_AMOUNT").toString() : null);
            dto.setRESPONSIPILITY_TYPE(row.get("RESPONSIPILITY_TYPE") != null ? row.get("RESPONSIPILITY_TYPE").toString() : null);
            dto.setSHARE_OS(row.get("SHARE_OS") != null ? row.get("SHARE_OS").toString() : null);
            dto.setTRANS_DATE_RESERVE(row.get("TRANS_DATE_RESERVE") != null ? row.get("TRANS_DATE_RESERVE").toString() : null);
            dto.setCOURT_CASE(row.get("COURT_CASE") != null ? row.get("COURT_CASE").toString() : null);
            dto.setINSURANCE_CO(row.get("INSURANCE_CO") != null ? row.get("INSURANCE_CO").toString() : null);
            dto.setINSURANCE_CO_CLAIMS_PAGE(row.get("INSURANCE_CO_CLAIMS_PAGE") != null ? row.get("INSURANCE_CO_CLAIMS_PAGE").toString() : null);

            return dto;
        }).toList();
    }

    public List<OsLatest> getOsLatest(
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
            Date asAtDate
    ) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("TAHA_PORTAL")
                .withProcedureName("OS_LATEST")
                .declareParameters(
                        new SqlParameter("P_AS_AT", Types.DATE),
                        new SqlOutParameter("P_REF_CURSOR", OracleTypes.CURSOR, new ColumnMapRowMapper())
                );

        Map<String, Object> result = jdbcCall.execute(
                new MapSqlParameterSource()
                        .addValue("P_AS_AT", asAtDate)
        );

        //return (List<Map<String, Object>>) result.get("P_REF_CURSOR");
        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("P_REF_CURSOR");

        return rows.stream().map(row -> {
            OsLatest dto = new OsLatest();

            // Convert everything to String safely
            dto.setBRANCH(row.get("BRANCH") != null ? row.get("BRANCH").toString() : null);
            dto.setBUSINESS_CLASS(row.get("BUSINESS_CLASS") != null ? row.get("BUSINESS_CLASS").toString() : null);
            dto.setBUSINESS_CODE(row.get("BUSINESS_CODE") != null ? row.get("BUSINESS_CODE").toString() : null);
            dto.setTERRITORY(row.get("TERRITORY") != null ? row.get("TERRITORY").toString() : null);
            dto.setPRODUCT_NAME(row.get("PRODUCT_NAME") != null ? row.get("PRODUCT_NAME").toString() : null);
            dto.setPRODUCT_CODE(row.get("PRODUCT_CODE") != null ? row.get("PRODUCT_CODE").toString() : null);
            dto.setPOLICY_TYPE(row.get("POLICY_TYPE") != null ? row.get("POLICY_TYPE").toString() : null);
            dto.setPOLICY_NUMBER(row.get("POLICY_NUMBER") != null ? row.get("POLICY_NUMBER").toString() : null);
            dto.setTYPE_OF_COVER(row.get("TYPE_OF_COVER") != null ? row.get("TYPE_OF_COVER").toString() : null);
            dto.setINSURED_NUMBER(row.get("INSURED_NUMBER") != null ? row.get("INSURED_NUMBER").toString() : null);
            dto.setINSURED_NAME(row.get("INSURED_NAME") != null ? row.get("INSURED_NAME").toString() : null);
            dto.setEFFECTIVE_DATE(row.get("EFFECTIVE_DATE") != null ? row.get("EFFECTIVE_DATE").toString() : null);
            dto.setEXPIRY_DATE(row.get("EXPIRY_DATE") != null ? row.get("EXPIRY_DATE").toString() : null);
            dto.setCITY(row.get("CITY") != null ? row.get("CITY").toString() : null);
            dto.setGENDER(row.get("GENDER") != null ? row.get("GENDER").toString() : null);
            dto.setAGE(row.get("AGE") != null ? row.get("AGE").toString() : null);
            dto.setCLAIM_NUMBER(row.get("CLAIM_NUMBER") != null ? row.get("CLAIM_NUMBER").toString() : null);
            dto.setDAMAGE_TYPE(row.get("DAMAGE_TYPE") != null ? row.get("DAMAGE_TYPE").toString() : null);
            dto.setCAR_PLATE(row.get("CAR_PLATE") != null ? row.get("CAR_PLATE").toString() : null);
            dto.setMT_CHASSIS_NO(row.get("MT_CHASSIS_NO") != null ? row.get("MT_CHASSIS_NO").toString() : null);
            dto.setCOLOR_CAR(row.get("COLOR_CAR") != null ? row.get("COLOR_CAR").toString() : null);
            dto.setCAR_CATEGORY(row.get("CAR_CATEGORY") != null ? row.get("CAR_CATEGORY").toString() : null);
            dto.setCAR_VALUE(row.get("CAR_VALUE") != null ? row.get("CAR_VALUE").toString() : null);
            dto.setMT_PROD_YEAR(row.get("MT_PROD_YEAR") != null ? row.get("MT_PROD_YEAR").toString() : null);
            dto.setCAR_BRAND(row.get("CAR_BRAND") != null ? row.get("CAR_BRAND").toString() : null);
            dto.setCAR_SERIE(row.get("CAR_SERIE") != null ? row.get("CAR_SERIE").toString() : null);
            dto.setCAR_TYPE(row.get("CAR_TYPE") != null ? row.get("CAR_TYPE").toString() : null);
            dto.setREGISTRATION_DATE_DD(row.get("REGISTRATION_DATE_DD") != null ? row.get("REGISTRATION_DATE_DD").toString() : null);
            dto.setREGISTRATION_DATE_MM(row.get("REGISTRATION_DATE_MM") != null ? row.get("REGISTRATION_DATE_MM").toString() : null);
            dto.setREGISTRATION_DATE_YYYY(row.get("REGISTRATION_DATE_YYYY") != null ? row.get("REGISTRATION_DATE_YYYY").toString() : null);
            dto.setDATE_OF_LOSS_DD(row.get("DATE_OF_LOSS_DD") != null ? row.get("DATE_OF_LOSS_DD").toString() : null);
            dto.setDATE_OF_LOSS_MM(row.get("DATE_OF_LOSS_MM") != null ? row.get("DATE_OF_LOSS_MM").toString() : null);
            dto.setDATE_OF_LOSS_YYYY(row.get("DATE_OF_LOSS_YYYY") != null ? row.get("DATE_OF_LOSS_YYYY").toString() : null);
            dto.setDATE_OF_REPORTING_DD(row.get("DATE_OF_REPORTING_DD") != null ? row.get("DATE_OF_REPORTING_DD").toString() : null);
            dto.setDATE_OF_REPORTING_MM(row.get("DATE_OF_REPORTING_MM") != null ? row.get("DATE_OF_REPORTING_MM").toString() : null);
            dto.setDATE_OF_REPORTING_YYYY(row.get("DATE_OF_REPORTING_YYYY") != null ? row.get("DATE_OF_REPORTING_YYYY").toString() : null);
            dto.setGROSS_CLM_OS_RESERVE(row.get("GROSS_CLM_OS_RESERVE") != null ? row.get("GROSS_CLM_OS_RESERVE").toString() : null);
            dto.setOD_MATERIAL_OS(row.get("OD_MATERIAL_OS") != null ? row.get("OD_MATERIAL_OS").toString() : null);
            dto.setOD_BODLY_OS(row.get("OD_BODLY_OS") != null ? row.get("OD_BODLY_OS").toString() : null);
            dto.setOD_DEATH_OS(row.get("OD_DEATH_OS") != null ? row.get("OD_DEATH_OS").toString() : null);
            dto.setTP_MATERIAL_OS(row.get("TP_MATERIAL_OS") != null ? row.get("TP_MATERIAL_OS").toString() : null);
            dto.setTP_BODLY_OS(row.get("TP_BODLY_OS") != null ? row.get("TP_BODLY_OS").toString() : null);
            dto.setTP_BODLY_OS_1(row.get("TP_BODLY_OS_1") != null ? row.get("TP_BODLY_OS_1").toString() : null);
            dto.setOS_7(row.get("OS_7") != null ? row.get("OS_7").toString() : null);
            dto.setOS_8(row.get("OS_8") != null ? row.get("OS_8").toString() : null);
            dto.setOS_9(row.get("OS_9") != null ? row.get("OS_9").toString() : null);
            dto.setOS_10(row.get("OS_10") != null ? row.get("OS_10").toString() : null);
            dto.setAMBULANCE_OS(row.get("AMBULANCE_OS") != null ? row.get("AMBULANCE_OS").toString() : null);
            dto.setOUTSTANDING_NON_RECOVERY(row.get("OUTSTANDING_NON_RECOVERY") != null ? row.get("OUTSTANDING_NON_RECOVERY").toString() : null);
            dto.setREINSURANCE_SHARE(row.get("REINSURANCE_SHARE") != null ? row.get("REINSURANCE_SHARE").toString() : null);
            dto.setGCL_OS_REINAURNCE_LOCAL_FAC(row.get("GCL_OS_REINAURNCE_LOCAL_FAC") != null ? row.get("GCL_OS_REINAURNCE_LOCAL_FAC").toString() : null);
            dto.setGCL_OS_REINAURNCE_FRN_FAC(row.get("GCL_OS_REINAURNCE_FRN_FAC") != null ? row.get("GCL_OS_REINAURNCE_FRN_FAC").toString() : null);
            dto.setTRANSACTION_TYPE(row.get("TRANSACTION_TYPE") != null ? row.get("TRANSACTION_TYPE").toString() : null);
            dto.setREINSURANCE_TYPE(row.get("REINSURANCE_TYPE") != null ? row.get("REINSURANCE_TYPE").toString() : null);
            dto.setPAID(row.get("PAID") != null ? row.get("PAID").toString() : null);
            dto.setCOM(row.get("COM") != null ? row.get("COM").toString() : null);
            dto.setTP(row.get("TP") != null ? row.get("TP").toString() : null);
            dto.setNATURE_OF_LOSS(row.get("NATURE_OF_LOSS") != null ? row.get("NATURE_OF_LOSS").toString() : null);
            dto.setPLC_UW_YEAR(row.get("PLC_UW_YEAR") != null ? row.get("PLC_UW_YEAR").toString() : null);
            dto.setCOURT_CASE(row.get("COURT_CASE") != null ? row.get("COURT_CASE").toString() : null);
            dto.setRESPONSIPILITY_TYPE(row.get("RESPONSIPILITY_TYPE") != null ? row.get("RESPONSIPILITY_TYPE").toString() : null);

            return dto;
        }).toList();
    }

    public List<ProductionDto> getProduction(
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
            Date fromDate,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
            Date toDate
    ) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("TAHA_PORTAL")
                .withProcedureName("PRODUCTION")
                .declareParameters(
                        new SqlParameter("P_FROM_DATE", Types.DATE),
                        new SqlParameter("P_TO_DATE", Types.DATE),
                        new SqlOutParameter("P_REF_CURSOR", OracleTypes.CURSOR, new ColumnMapRowMapper())
                );

        Map<String, Object> result = jdbcCall.execute(
                new MapSqlParameterSource()
                        .addValue("P_FROM_DATE", fromDate)
                        .addValue("P_TO_DATE", toDate)
        );

        //jdbcTemplate.setFetchSize(1000000);
        jdbcCall.getJdbcTemplate().setQueryTimeout(1800); // 30 دقائق

        //return (List<Map<String, Object>>) result.get("P_REF_CURSOR");
        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("P_REF_CURSOR");

        return rows.stream().map(row -> {
            ProductionDto dto = new ProductionDto();

            // Convert everything to String safely
            dto.setID(row.get("ID") != null ? row.get("ID").toString() : null);
            dto.setBRANCH(row.get("BRANCH") != null ? row.get("BRANCH").toString() : null);
            dto.setBUSINESS_CLASS(row.get("BUSINESS_CLASS") != null ? row.get("BUSINESS_CLASS").toString() : null);
            dto.setBUSINESS_CODE(row.get("BUSINESS_CODE") != null ? row.get("BUSINESS_CODE").toString() : null);
            dto.setPRODUCT_CODE(row.get("PRODUCT_CODE") != null ? row.get("PRODUCT_CODE").toString() : null);
            dto.setINSURED_NUMBER(row.get("INSURED_NUMBER") != null ? row.get("INSURED_NUMBER").toString() : null);
            dto.setINSURED(row.get("INSURED") != null ? row.get("INSURED").toString() : null);
            dto.setACCOUNT_NUMBER(row.get("ACCOUNT_NUMBER") != null ? row.get("ACCOUNT_NUMBER").toString() : null);
            dto.setACCOUNT_NAME(row.get("ACCOUNT_NAME") != null ? row.get("ACCOUNT_NAME").toString() : null);
            dto.setBENEFICIARY_NUMBER(row.get("BENEFICIARY_NUMBER") != null ? row.get("BENEFICIARY_NUMBER").toString() : null);
            dto.setBENIFICARY_NAME(row.get("BENIFICARY_NAME") != null ? row.get("BENIFICARY_NAME").toString() : null);
            dto.setACCOUNT_TYPE(row.get("ACCOUNT_TYPE") != null ? row.get("ACCOUNT_TYPE").toString() : null);
            dto.setPOLICY_NUMBER(row.get("POLICY_NUMBER") != null ? row.get("POLICY_NUMBER").toString() : null);
            dto.setENDORSEMENT_NUMBER(row.get("ENDORSEMENT_NUMBER") != null ? row.get("ENDORSEMENT_NUMBER").toString() : null);
            dto.setENDORSEMENT_DESCRIPTION(row.get("ENDORSEMENT_DESCRIPTION") != null ? row.get("ENDORSEMENT_DESCRIPTION").toString() : null);
            dto.setTERRITORY(row.get("TERRITORY") != null ? row.get("TERRITORY").toString() : null);
            dto.setPOLICY_TYPE(row.get("POLICY_TYPE") != null ? row.get("POLICY_TYPE").toString() : null);
            dto.setDIRECT_CHANNELS(row.get("DIRECT_CHANNELS") != null ? row.get("DIRECT_CHANNELS").toString() : null);
            dto.setBROKER(row.get("BROKER") != null ? row.get("BROKER").toString() : null);
            dto.setGENDER(row.get("GENDER") != null ? row.get("GENDER").toString() : null);
            dto.setAGE(row.get("AGE") != null ? row.get("AGE").toString() : null);
            dto.setMT_ENGINE_NO2(row.get("MT_ENGINE_NO2") != null ? row.get("MT_ENGINE_NO2").toString() : null);
            dto.setCAR_PLATE(row.get("CAR_PLATE") != null ? row.get("CAR_PLATE").toString() : null);
            dto.setMT_CHASSIS_NO(row.get("MT_CHASSIS_NO") != null ? row.get("MT_CHASSIS_NO").toString() : null);
            dto.setCAR_COLOR(row.get("CAR_COLOR") != null ? row.get("CAR_COLOR").toString() : null);
            dto.setMT_ENGINE_SIZE(row.get("MT_ENGINE_SIZE") != null ? row.get("MT_ENGINE_SIZE").toString() : null);
            dto.setCAR_CATEGORY(row.get("CAR_CATEGORY") != null ? row.get("CAR_CATEGORY").toString() : null);
            dto.setCAR_VALUE(row.get("CAR_VALUE") != null ? row.get("CAR_VALUE").toString() : null);
            dto.setMAKE_YEAR(row.get("MAKE_YEAR") != null ? row.get("MAKE_YEAR").toString() : null);
            dto.setCAR_BRAND(row.get("CAR_BRAND") != null ? row.get("CAR_BRAND").toString() : null);
            dto.setCAR_SERIE(row.get("CAR_SERIE") != null ? row.get("CAR_SERIE").toString() : null);
            dto.setCAR_TYPE(row.get("CAR_TYPE") != null ? row.get("CAR_TYPE").toString() : null);
            dto.setCYLINDER(row.get("CYLINDER") != null ? row.get("CYLINDER").toString() : null);
            dto.setNO_OF_SEATS(row.get("NO_OF_SEATS") != null ? row.get("NO_OF_SEATS").toString() : null);
            dto.setHORSEPOWER(row.get("HORSEPOWER") != null ? row.get("HORSEPOWER").toString() : null);
            dto.setUSAGE(row.get("USAGE") != null ? row.get("USAGE").toString() : null);
            dto.setREPAIR_CONDITION(row.get("REPAIR_CONDITION") != null ? row.get("REPAIR_CONDITION").toString() : null);
            dto.setBODY_TYPE(row.get("BODY_TYPE") != null ? row.get("BODY_TYPE").toString() : null);
            dto.setDATE_ISS_DAY(row.get("DATE_ISS_DAY") != null ? row.get("DATE_ISS_DAY").toString() : null);
            dto.setDATE_ISS_MONTH(row.get("DATE_ISS_MONTH") != null ? row.get("DATE_ISS_MONTH").toString() : null);
            dto.setDATE_ISS_YEAR(row.get("DATE_ISS_YEAR") != null ? row.get("DATE_ISS_YEAR").toString() : null);
            dto.setORIGINAL_EFF_DAY(row.get("ORIGINAL_EFF_DAY") != null ? row.get("ORIGINAL_EFF_DAY").toString() : null);
            dto.setORIGINAL_EFF_MONTH(row.get("ORIGINAL_EFF_MONTH") != null ? row.get("ORIGINAL_EFF_MONTH").toString() : null);
            dto.setORIGINAL_EFF_YEAR(row.get("ORIGINAL_EFF_YEAR") != null ? row.get("ORIGINAL_EFF_YEAR").toString() : null);
            dto.setDATE_EFF_DAY(row.get("DATE_EFF_DAY") != null ? row.get("DATE_EFF_DAY").toString() : null);
            dto.setDATE_EFF_MONTH(row.get("DATE_EFF_MONTH") != null ? row.get("DATE_EFF_MONTH").toString() : null);
            dto.setDATE_EFF_YEAR(row.get("DATE_EFF_YEAR") != null ? row.get("DATE_EFF_YEAR").toString() : null);
            dto.setDATE_EXP_DAY(row.get("DATE_EXP_DAY") != null ? row.get("DATE_EXP_DAY").toString() : null);
            dto.setDATE_EXP_MONTH(row.get("DATE_EXP_MONTH") != null ? row.get("DATE_EXP_MONTH").toString() : null);
            dto.setDEDUCTIBLE(row.get("DEDUCTIBLE") != null ? row.get("DEDUCTIBLE").toString() : null);
            dto.setDATE_EXP_YEAR(row.get("DATE_EXP_YEAR") != null ? row.get("DATE_EXP_YEAR").toString() : null);
            dto.setCOINSURANCE(row.get("COINSURANCE") != null ? row.get("COINSURANCE").toString() : null);
            dto.setSUM_INSURED(row.get("SUM_INSURED") != null ? row.get("SUM_INSURED").toString() : null);
            dto.setGROSS_PREMIUM(row.get("GROSS_PREMIUM") != null ? row.get("GROSS_PREMIUM").toString() : null);
            dto.setDISCOUNT_APPLIED(row.get("DISCOUNT_APPLIED") != null ? row.get("DISCOUNT_APPLIED").toString() : null);
            dto.setPREM_AFTER_DIOSCOUNT(row.get("PREM_AFTER_DIOSCOUNT") != null ? row.get("PREM_AFTER_DIOSCOUNT").toString() : null);
            dto.setGROSS_TPL_PREM(row.get("GROSS_TPL_PREM") != null ? row.get("GROSS_TPL_PREM").toString() : null);
            dto.setGROSS_COMP_PREM(row.get("GROSS_COMP_PREM") != null ? row.get("GROSS_COMP_PREM").toString() : null);
            dto.setOTHER_PREMIUM(row.get("OTHER_PREMIUM") != null ? row.get("OTHER_PREMIUM").toString() : null);
            dto.setPOL_ISS_FEES(row.get("POL_ISS_FEES") != null ? row.get("POL_ISS_FEES").toString() : null);
            dto.setINSURED_NATIONALITY(row.get("INSURED_NATIONALITY") != null ? row.get("INSURED_NATIONALITY").toString() : null);
            dto.setRED_CITY(row.get("RED_CITY") != null ? row.get("RED_CITY").toString() : null);
            dto.setCUSTOMER_TYPES(row.get("CUSTOMER_TYPES") != null ? row.get("CUSTOMER_TYPES").toString() : null);
            dto.setCOMMISSION_PAID(row.get("COMMISSION_PAID") != null ? row.get("COMMISSION_PAID").toString() : null);
            dto.setREINSURANCE_TYPE(row.get("REINSURANCE_TYPE") != null ? row.get("REINSURANCE_TYPE").toString() : null);
            dto.setCLAIM_NO(row.get("CLAIM_NO") != null ? row.get("CLAIM_NO").toString() : null);
            dto.setBUSINESS_TYPES(row.get("BUSINESS_TYPES") != null ? row.get("BUSINESS_TYPES").toString() : null);
            dto.setREINSURANCE_COMMISSION(row.get("REINSURANCE_COMMISSION") != null ? row.get("REINSURANCE_COMMISSION").toString() : null);
            dto.setCLAIM_NO_1(row.get("CLAIM_NO_1") != null ? row.get("CLAIM_NO_1").toString() : null);
            dto.setREGISTRATION_CITY(row.get("REGISTRATION_CITY") != null ? row.get("REGISTRATION_CITY").toString() : null);
            dto.setADDRESS(row.get("ADDRESS") != null ? row.get("ADDRESS").toString() : null);
            dto.setREINSURANCE_SHARE(row.get("REINSURANCE_SHARE") != null ? row.get("REINSURANCE_SHARE").toString() : null);
            dto.setREINSURANCE_SHARE_FAC(row.get("REINSURANCE_SHARE_FAC") != null ? row.get("REINSURANCE_SHARE_FAC").toString() : null);
            dto.setREINSURANCE_SHARE_LOCAL(row.get("REINSURANCE_SHARE_LOCAL") != null ? row.get("REINSURANCE_SHARE_LOCAL").toString() : null);
            dto.setCESSION_TYPE(row.get("CESSION_TYPE") != null ? row.get("CESSION_TYPE").toString() : null);
            dto.setFAC_REINSURANCE_COMMISSION(row.get("FAC_REINSURANCE_COMMISSION") != null ? row.get("FAC_REINSURANCE_COMMISSION").toString() : null);
            dto.setTOTAL_REINSURANCE_COMMISSION(row.get("TOTAL_REINSURANCE_COMMISSION") != null ? row.get("TOTAL_REINSURANCE_COMMISSION").toString() : null);
            dto.setCBJ_FEES(row.get("CBJ_FEES") != null ? row.get("CBJ_FEES").toString() : null);
            dto.setSALES_CBJ_FEES(row.get("SALES_CBJ_FEES") != null ? row.get("SALES_CBJ_FEES").toString() : null);
            dto.setFUEL_TYPE(row.get("FUEL_TYPE") != null ? row.get("FUEL_TYPE").toString() : null);
            dto.setINWARD_COMM(row.get("INWARD_COMM") != null ? row.get("INWARD_COMM").toString() : null);
            dto.setDIRECT_COMM(row.get("DIRECT_COMM") != null ? row.get("DIRECT_COMM").toString() : null);
            dto.setCOMM_TYPE(row.get("COMM_TYPE") != null ? row.get("COMM_TYPE").toString() : null);
            dto.setCBJ4_5(row.get("CBJ4_5") != null ? row.get("CBJ4_5").toString() : null);
            dto.setCBJ_0_1(row.get("CBJ_0_1") != null ? row.get("CBJ_0_1").toString() : null);
            dto.setCREATED_BY(row.get("CREATED_BY") != null ? row.get("CREATED_BY").toString() : null);

            return dto;
        }).toList();
    }

    public List<PaidRecoveryLatestDto> getPaidRecoveryLatest(
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
            Date fromDate,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
            Date toDate
    ) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("TAHA_PORTAL")
                .withProcedureName("PAID_RECOVERY_LATEST")
                .declareParameters(
                        new SqlParameter("P_FROM_DATE", Types.DATE),
                        new SqlParameter("P_TO_DATE", Types.DATE),
                        new SqlOutParameter("P_REF_CURSOR", OracleTypes.CURSOR, new ColumnMapRowMapper())
                );

        Map<String, Object> result = jdbcCall.execute(
                new MapSqlParameterSource()
                        .addValue("P_FROM_DATE", fromDate)
                        .addValue("P_TO_DATE", toDate)
        );

        //jdbcTemplate.setFetchSize(1000000);
        jdbcCall.getJdbcTemplate().setQueryTimeout(1800); // 30 دقائق

        //return (List<Map<String, Object>>) result.get("P_REF_CURSOR");
        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("P_REF_CURSOR");

        return rows.stream().map(row -> {
            PaidRecoveryLatestDto dto = new PaidRecoveryLatestDto();

            // Convert everything to String safely
            dto.setBRANCH(row.get("BRANCH") != null ? row.get("BRANCH").toString() : null);
            dto.setBUSINESS_CLASS(row.get("BUSINESS_CLASS") != null ? row.get("BUSINESS_CLASS").toString() : null);
            dto.setBUSINESS_CODE(row.get("BUSINESS_CODE") != null ? row.get("BUSINESS_CODE").toString() : null);
            dto.setPRODUCT_NAME(row.get("PRODUCT_NAME") != null ? row.get("PRODUCT_NAME").toString() : null);
            dto.setPRODUCT_CODE(row.get("PRODUCT_CODE") != null ? row.get("PRODUCT_CODE").toString() : null);
            dto.setACCOUNT_TYPE(row.get("ACCOUNT_TYPE") != null ? row.get("ACCOUNT_TYPE").toString() : null);
            dto.setTERRITORY(row.get("TERRITORY") != null ? row.get("TERRITORY").toString() : null);
            dto.setPOLICY_NUMBER(row.get("POLICY_NUMBER") != null ? row.get("POLICY_NUMBER").toString() : null);
            dto.setEFFECTIVE_DATE(row.get("EFFECTIVE_DATE") != null ? row.get("EFFECTIVE_DATE").toString() : null);
            dto.setEXPIRY_DATE(row.get("EXPIRY_DATE") != null ? row.get("EXPIRY_DATE").toString() : null);
            dto.setINSURED_NUMBER(row.get("INSURED_NUMBER") != null ? row.get("INSURED_NUMBER").toString() : null);
            dto.setGENDER(row.get("GENDER") != null ? row.get("GENDER").toString() : null);
            dto.setAGE(row.get("AGE") != null ? row.get("AGE").toString() : null);
            dto.setCLAIM_NUMBER(row.get("CLAIM_NUMBER") != null ? row.get("CLAIM_NUMBER").toString() : null);
            dto.setDATE_OF_LOSS_DD(row.get("DATE_OF_LOSS_DD") != null ? row.get("DATE_OF_LOSS_DD").toString() : null);
            dto.setDATE_OF_LOSS_MM(row.get("DATE_OF_LOSS_MM") != null ? row.get("DATE_OF_LOSS_MM").toString() : null);
            dto.setDATE_OF_LOSS_YYYY(row.get("DATE_OF_LOSS_YYYY") != null ? row.get("DATE_OF_LOSS_YYYY").toString() : null);
            dto.setDATE_OF_REPORTING_DD(row.get("DATE_OF_REPORTING_DD") != null ? row.get("DATE_OF_REPORTING_DD").toString() : null);
            dto.setDATE_OF_REPORTING_MM(row.get("DATE_OF_REPORTING_MM") != null ? row.get("DATE_OF_REPORTING_MM").toString() : null);
            dto.setDATE_OF_REPORTING_YYYY(row.get("DATE_OF_REPORTING_YYYY") != null ? row.get("DATE_OF_REPORTING_YYYY").toString() : null);
            dto.setDATE_OF_TRANSACTION_DD(row.get("DATE_OF_TRANSACTION_DD") != null ? row.get("DATE_OF_TRANSACTION_DD").toString() : null);
            dto.setDATE_OF_TRANSACTION_MM(row.get("DATE_OF_TRANSACTION_MM") != null ? row.get("DATE_OF_TRANSACTION_MM").toString() : null);
            dto.setDATE_OF_TRANSACTION_YYYY(row.get("DATE_OF_TRANSACTION_YYYY") != null ? row.get("DATE_OF_TRANSACTION_YYYY").toString() : null);
            dto.setTRANSACTION_AMOUNT(row.get("TRANSACTION_AMOUNT") != null ? row.get("TRANSACTION_AMOUNT").toString() : null);
            dto.setTP_MATREIAL(row.get("TP_MATREIAL") != null ? row.get("TP_MATREIAL").toString() : null);
            dto.setTP_BODLY(row.get("TP_BODLY") != null ? row.get("TP_BODLY").toString() : null);
            dto.setTP_DEATH(row.get("TP_DEATH") != null ? row.get("TP_DEATH").toString() : null);
            dto.setOD_MATREIAL(row.get("OD_MATREIAL") != null ? row.get("OD_MATREIAL").toString() : null);
            dto.setOD_BODLY(row.get("OD_BODLY") != null ? row.get("OD_BODLY").toString() : null);
            dto.setOD_DEATH(row.get("OD_DEATH") != null ? row.get("OD_DEATH").toString() : null);
            dto.setOTHERS(row.get("OTHERS") != null ? row.get("OTHERS").toString() : null);
            dto.setSUM_PAYM_TRANS_DET(row.get("SUM_PAYM_TRANS_DET") != null ? row.get("SUM_PAYM_TRANS_DET").toString() : null);
            dto.setINSURANCE_COMPANY(row.get("INSURANCE_COMPANY") != null ? row.get("INSURANCE_COMPANY").toString() : null);
            dto.setINDIVIDUAL(row.get("INDIVIDUAL") != null ? row.get("INDIVIDUAL").toString() : null);
            dto.setSELLING_SCRAP(row.get("SELLING_SCRAP") != null ? row.get("SELLING_SCRAP").toString() : null);
            dto.setDEDUCTIBLE(row.get("DEDUCTIBLE") != null ? row.get("DEDUCTIBLE").toString() : null);
            dto.setDEPRECIATION(row.get("DEPRECIATION") != null ? row.get("DEPRECIATION").toString() : null);
            dto.setSHIP_OWNERS(row.get("SHIP_OWNERS") != null ? row.get("SHIP_OWNERS").toString() : null);
            dto.setP_I_CLUB(row.get("P_I_CLUB") != null ? row.get("P_I_CLUB").toString() : null);
            dto.setHAULER(row.get("HAULER") != null ? row.get("HAULER").toString() : null);
            dto.setGENERAL_AVERAGE(row.get("GENERAL_AVERAGE") != null ? row.get("GENERAL_AVERAGE").toString() : null);
            dto.setRECOVERY_FROM_INSURED(row.get("RECOVERY_FROM_INSURED") != null ? row.get("RECOVERY_FROM_INSURED").toString() : null);
            dto.setDED_DEPRETIATION(row.get("DED_DEPRETIATION") != null ? row.get("DED_DEPRETIATION").toString() : null);
            dto.setDEBITOR_DED_DEPRETIATION(row.get("DEBITOR_DED_DEPRETIATION") != null ? row.get("DEBITOR_DED_DEPRETIATION").toString() : null);
            dto.setREINSURANCE_SHARE(row.get("REINSURANCE_SHARE") != null ? row.get("REINSURANCE_SHARE").toString() : null);
            dto.setREINSURANCE_LOCAL_SHARE(row.get("REINSURANCE_LOCAL_SHARE") != null ? row.get("REINSURANCE_LOCAL_SHARE").toString() : null);
            dto.setREINSURANCE_FRN_SHARE(row.get("REINSURANCE_FRN_SHARE") != null ? row.get("REINSURANCE_FRN_SHARE").toString() : null);
            dto.setTRANSACTION_TYPE(row.get("TRANSACTION_TYPE") != null ? row.get("TRANSACTION_TYPE").toString() : null);
            dto.setREINSURANCE_TYPE(row.get("REINSURANCE_TYPE") != null ? row.get("REINSURANCE_TYPE").toString() : null);
            dto.setCOURT_CASE(row.get("COURT_CASE") != null ? row.get("COURT_CASE").toString() : null);
            dto.setCAR_PLATE(row.get("CAR_PLATE") != null ? row.get("CAR_PLATE").toString() : null);
            dto.setMT_CHASSIS_NO(row.get("MT_CHASSIS_NO") != null ? row.get("MT_CHASSIS_NO").toString() : null);
            dto.setCAR_PLATE_FROM_CLAIM(row.get("CAR_PLATE_FROM_CLAIM") != null ? row.get("CAR_PLATE_FROM_CLAIM").toString() : null);
            dto.setMT_CHASSIS_NO_FROM_CLAIM(row.get("MT_CHASSIS_NO_FROM_CLAIM") != null ? row.get("MT_CHASSIS_NO_FROM_CLAIM").toString() : null);
            dto.setCOLOR_CAR(row.get("COLOR_CAR") != null ? row.get("COLOR_CAR").toString() : null);
            dto.setCAR_CATEGORY(row.get("CAR_CATEGORY") != null ? row.get("CAR_CATEGORY").toString() : null);
            dto.setCAR_VALUE(row.get("CAR_VALUE") != null ? row.get("CAR_VALUE").toString() : null);
            dto.setMAKE_YEAR(row.get("MAKE_YEAR") != null ? row.get("MAKE_YEAR").toString() : null);
            dto.setCAR_BRAND(row.get("CAR_BRAND") != null ? row.get("CAR_BRAND").toString() : null);
            dto.setCAR_SERIE(row.get("CAR_SERIE") != null ? row.get("CAR_SERIE").toString() : null);
            dto.setCAR_TYPE(row.get("CAR_TYPE") != null ? row.get("CAR_TYPE").toString() : null);
            dto.setPLC_UW_YEAR(row.get("PLC_UW_YEAR") != null ? row.get("PLC_UW_YEAR").toString() : null);
            dto.setNATURE_OF_LOSS(row.get("NATURE_OF_LOSS") != null ? row.get("NATURE_OF_LOSS").toString() : null);
            dto.setEFFECTIVEDATE(row.get("EFFECTIVEDATE") != null ? row.get("EFFECTIVEDATE").toString() : null);
            dto.setEXPIRYDATE(row.get("EXPIRYDATE") != null ? row.get("EXPIRYDATE").toString() : null);
            dto.setCITY(row.get("CITY") != null ? row.get("CITY").toString() : null);
            dto.setINSURED_NATIONALITY(row.get("INSURED_NATIONALITY") != null ? row.get("INSURED_NATIONALITY").toString() : null);
            dto.setINSURED_NATIONALITY2(row.get("INSURED_NATIONALITY2") != null ? row.get("INSURED_NATIONALITY2").toString() : null);
            dto.setRED_CITY(row.get("RED_CITY") != null ? row.get("RED_CITY").toString() : null);
            dto.setCLAIM_STATUS(row.get("CLAIM_STATUS") != null ? row.get("CLAIM_STATUS").toString() : null);
            dto.setCST_NAME(row.get("CST_NAME") != null ? row.get("CST_NAME").toString() : null);
            dto.setPURCHASE_ORDER(row.get("PURCHASE_ORDER") != null ? row.get("PURCHASE_ORDER").toString() : null);
            dto.setREPAIR_ORDER(row.get("REPAIR_ORDER") != null ? row.get("REPAIR_ORDER").toString() : null);
            dto.setRESPONSIPILITY_TYPE(row.get("RESPONSIPILITY_TYPE") != null ? row.get("RESPONSIPILITY_TYPE").toString() : null);

            return dto;
        }).toList();
    }

    public List<RepairPurchaseOrderDto> getRepairPurchaseOrder(
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
            Date fromDate,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
            Date toDate
    ) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("TAHA_PORTAL")
                .withProcedureName("REPAIR_PURCHASE_ORDER")
                .declareParameters(
                        new SqlParameter("P_FROM_DATE", Types.DATE),
                        new SqlParameter("P_TO_DATE", Types.DATE),
                        new SqlOutParameter("P_REF_CURSOR", OracleTypes.CURSOR, new ColumnMapRowMapper())
                );

        Map<String, Object> result = jdbcCall.execute(
                new MapSqlParameterSource()
                        .addValue("P_FROM_DATE", fromDate)
                        .addValue("P_TO_DATE", toDate)
        );

        //jdbcTemplate.setFetchSize(1000000);
        jdbcCall.getJdbcTemplate().setQueryTimeout(1800); // 30 دقائق

        //return (List<Map<String, Object>>) result.get("P_REF_CURSOR");
        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("P_REF_CURSOR");

        return rows.stream().map(row -> {
            RepairPurchaseOrderDto dto = new RepairPurchaseOrderDto();

            // Convert everything to String safely
            dto.setCLAIM_NUMBER(row.get("CLAIM_NUMBER") != null ? row.get("CLAIM_NUMBER").toString() : null);
            dto.setCLAIM_STATUS(row.get("CLAIM_STATUS") != null ? row.get("CLAIM_STATUS").toString() : null);
            dto.setCLAIMANT(row.get("CLAIMANT") != null ? row.get("CLAIMANT").toString() : null);
            dto.setCLAIMANT_CREATED_BY(row.get("CLAIMANT_CREATED_BY") != null ? row.get("CLAIMANT_CREATED_BY").toString() : null);
            dto.setCLAIMANT_CREATION_DATE(row.get("CLAIMANT_CREATION_DATE") != null ? row.get("CLAIMANT_CREATION_DATE").toString() : null);
            dto.setCLAIMANT_MODIFIED_BY(row.get("CLAIMANT_MODIFIED_BY") != null ? row.get("CLAIMANT_MODIFIED_BY").toString() : null);
            dto.setCLAIMANT_MODIFICATION_DATE(row.get("CLAIMANT_MODIFICATION_DATE") != null ? row.get("CLAIMANT_MODIFICATION_DATE").toString() : null);
            dto.setORDER_NUMBER(row.get("ORDER_NUMBER") != null ? row.get("ORDER_NUMBER").toString() : null);
            dto.setREQUEST_CREATED_BY(row.get("REQUEST_CREATED_BY") != null ? row.get("REQUEST_CREATED_BY").toString() : null);
            dto.setPURCHASE_ORDER_SUPPLIER_TYPE(row.get("PURCHASE_ORDER_SUPPLIER_TYPE") != null ? row.get("PURCHASE_ORDER_SUPPLIER_TYPE").toString() : null);
            dto.setPURCHASE_ORDER_SUPPLIER_NAME(row.get("PURCHASE_ORDER_SUPPLIER_NAME") != null ? row.get("PURCHASE_ORDER_SUPPLIER_NAME").toString() : null);
            dto.setPURCHASE_ORDER_DESCRIPTION(row.get("PURCHASE_ORDER_DESCRIPTION") != null ? row.get("PURCHASE_ORDER_DESCRIPTION").toString() : null);
            dto.setPURCHASE_ORDER_AMOUNT(row.get("PURCHASE_ORDER_AMOUNT") != null ? row.get("PURCHASE_ORDER_AMOUNT").toString() : null);
            dto.setPURCHASE_ORDER_DATE(row.get("PURCHASE_ORDER_DATE") != null ? row.get("PURCHASE_ORDER_DATE").toString() : null);
            dto.setPURCHASE_STATUS_DATE(row.get("PURCHASE_STATUS_DATE") != null ? row.get("PURCHASE_STATUS_DATE").toString() : null);
            dto.setPURCHASE_CREATED_BY(row.get("PURCHASE_CREATED_BY") != null ? row.get("PURCHASE_CREATED_BY").toString() : null);
            dto.setPURCHASE_CREATION_DATE(row.get("PURCHASE_CREATION_DATE") != null ? row.get("PURCHASE_CREATION_DATE").toString() : null);
            dto.setPURCHASE_MODIFIED_BY(row.get("PURCHASE_MODIFIED_BY") != null ? row.get("PURCHASE_MODIFIED_BY").toString() : null);
            dto.setPURCHASE_MODIFICATION_DATE(row.get("PURCHASE_MODIFICATION_DATE") != null ? row.get("PURCHASE_MODIFICATION_DATE").toString() : null);
            dto.setPURCHASE_PAYMENT_TRANS_AMT(row.get("PURCHASE_PAYMENT_TRANS_AMT") != null ? row.get("PURCHASE_PAYMENT_TRANS_AMT").toString() : null);
            dto.setPURCHASE_PAYMENT_FEE_AMOUNT(row.get("PURCHASE_PAYMENT_FEE_AMOUNT") != null ? row.get("PURCHASE_PAYMENT_FEE_AMOUNT").toString() : null);
            dto.setPURCHASE_PAYMENT_NET_TRANS_AMT(row.get("PURCHASE_PAYMENT_NET_TRANS_AMT") != null ? row.get("PURCHASE_PAYMENT_NET_TRANS_AMT").toString() : null);
            dto.setREPAIR_ORDER_SUPPLIER_TYPE(row.get("REPAIR_ORDER_SUPPLIER_TYPE") != null ? row.get("REPAIR_ORDER_SUPPLIER_TYPE").toString() : null);
            dto.setREPAIR_ORDER_SUPPLIER_NAME(row.get("REPAIR_ORDER_SUPPLIER_NAME") != null ? row.get("REPAIR_ORDER_SUPPLIER_NAME").toString() : null);
            dto.setREPAIR_ORDER_DESCRIPTION(row.get("REPAIR_ORDER_DESCRIPTION") != null ? row.get("REPAIR_ORDER_DESCRIPTION").toString() : null);
            dto.setREPAIR_ORDER_AMOUNT(row.get("REPAIR_ORDER_AMOUNT") != null ? row.get("REPAIR_ORDER_AMOUNT").toString() : null);
            dto.setREPAIR_ORDER_DATE(row.get("REPAIR_ORDER_DATE") != null ? row.get("REPAIR_ORDER_DATE").toString() : null);
            dto.setREPAIR_PAYMENT_TRANS_AMT(row.get("REPAIR_PAYMENT_TRANS_AMT") != null ? row.get("REPAIR_PAYMENT_TRANS_AMT").toString() : null);
            dto.setREPAIR_PAYMENT_FEE_AMOUNT(row.get("REPAIR_PAYMENT_FEE_AMOUNT") != null ? row.get("REPAIR_PAYMENT_FEE_AMOUNT").toString() : null);
            dto.setREPAIR_PAYMENT_NET_TRANS_AMT(row.get("REPAIR_PAYMENT_NET_TRANS_AMT") != null ? row.get("REPAIR_PAYMENT_NET_TRANS_AMT").toString() : null);
            dto.setREPAIR_STATUS_DATE(row.get("REPAIR_STATUS_DATE") != null ? row.get("REPAIR_STATUS_DATE").toString() : null);
            dto.setREPAIR_CREATED_BY(row.get("REPAIR_CREATED_BY") != null ? row.get("REPAIR_CREATED_BY").toString() : null);
            dto.setREPAIR_CREATION_DATE(row.get("REPAIR_CREATION_DATE") != null ? row.get("REPAIR_CREATION_DATE").toString() : null);
            dto.setREPAIR_MODIFIED_BY(row.get("REPAIR_MODIFIED_BY") != null ? row.get("REPAIR_MODIFIED_BY").toString() : null);
            dto.setREPAIR_MODIFICATION_DATE(row.get("REPAIR_MODIFICATION_DATE") != null ? row.get("REPAIR_MODIFICATION_DATE").toString() : null);
            dto.setPURCHASE_ORDER_PARTS(row.get("PURCHASE_ORDER_PARTS") != null ? row.get("PURCHASE_ORDER_PARTS").toString() : null);
            dto.setMT_VEHICLE_TYPE(row.get("MT_VEHICLE_TYPE") != null ? row.get("MT_VEHICLE_TYPE").toString() : null);
            dto.setMT_VEHICLE_MODEL(row.get("MT_VEHICLE_MODEL") != null ? row.get("MT_VEHICLE_MODEL").toString() : null);
            dto.setMT_COLOR(row.get("MT_COLOR") != null ? row.get("MT_COLOR").toString() : null);
            dto.setMT_CATEGORY(row.get("MT_CATEGORY") != null ? row.get("MT_CATEGORY").toString() : null);
            dto.setMT_VEHICLE_BODY(row.get("MT_VEHICLE_BODY") != null ? row.get("MT_VEHICLE_BODY").toString() : null);
            dto.setMT_PROD_YEAR(row.get("MT_PROD_YEAR") != null ? row.get("MT_PROD_YEAR").toString() : null);
            dto.setDAMEG_PART_1(row.get("DAMEG_PART_1") != null ? row.get("DAMEG_PART_1").toString() : null);
            dto.setDAMEG_PART_2(row.get("DAMEG_PART_2") != null ? row.get("DAMEG_PART_2").toString() : null);
            dto.setDAMEG_PART_3(row.get("DAMEG_PART_3") != null ? row.get("DAMEG_PART_3").toString() : null);
            dto.setDAMEG_PART_4(row.get("DAMEG_PART_4") != null ? row.get("DAMEG_PART_4").toString() : null);
            dto.setDAMEG_PART_5(row.get("DAMEG_PART_5") != null ? row.get("DAMEG_PART_5").toString() : null);
            dto.setDAMEG_PART_6(row.get("DAMEG_PART_6") != null ? row.get("DAMEG_PART_6").toString() : null);
            dto.setDAMEG_PART_7(row.get("DAMEG_PART_7") != null ? row.get("DAMEG_PART_7").toString() : null);
            dto.setDAMEG_PART_8(row.get("DAMEG_PART_8") != null ? row.get("DAMEG_PART_8").toString() : null);
            dto.setDAMEG_PART_9(row.get("DAMEG_PART_9") != null ? row.get("DAMEG_PART_9").toString() : null);
            dto.setDAMEG_PART_10(row.get("DAMEG_PART_10") != null ? row.get("DAMEG_PART_10").toString() : null);
            dto.setDAMEG_PART_11(row.get("DAMEG_PART_11") != null ? row.get("DAMEG_PART_11").toString() : null);
            dto.setDAMEG_PART_12(row.get("DAMEG_PART_12") != null ? row.get("DAMEG_PART_12").toString() : null);
            dto.setDAMEG_PART_13(row.get("DAMEG_PART_13") != null ? row.get("DAMEG_PART_13").toString() : null);
            dto.setDAMEG_PART_14(row.get("DAMEG_PART_14") != null ? row.get("DAMEG_PART_14").toString() : null);
            dto.setDAMEG_PART_15(row.get("DAMEG_PART_15") != null ? row.get("DAMEG_PART_15").toString() : null);
            dto.setDAMEG_PART_16(row.get("DAMEG_PART_16") != null ? row.get("DAMEG_PART_16").toString() : null);
            dto.setDAMEG_PART_17(row.get("DAMEG_PART_17") != null ? row.get("DAMEG_PART_17").toString() : null);

            return dto;
        }).toList();
    }
}
