package com.jicjo.apis.repository.icp;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.jicjo.apis.dto.icp.MedicalMembersDto;
import com.jicjo.apis.dto.icp.MedicalPoliciesDto;
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
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class MedicalPoliciesRepository implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MedicalPoliciesDto> getMedicalPolicies
            (Long mpdPlcId,
             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
             Date fromIssueDate,
             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Amman")
             Date toIssueDate) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICAL_ICP")
                .withProcedureName("GET_MEDICAL_POLICIES")
                .declareParameters(
                        new SqlParameter("P_MPD_PLC_ID", Types.NUMERIC),
                        new SqlParameter("P_FROM_ISSUE_DATE", Types.DATE),
                        new SqlParameter("P_TO_ISSUE_DATE", Types.DATE),
                        new SqlOutParameter("P_REF_CURSOR", OracleTypes.CURSOR, new ColumnMapRowMapper())
                );

        Map<String, Object> result = jdbcCall.execute(
                new MapSqlParameterSource()
                        .addValue("P_MPD_PLC_ID", mpdPlcId)
                        .addValue("P_FROM_ISSUE_DATE", fromIssueDate)
                        .addValue("P_TO_ISSUE_DATE", toIssueDate)
        );

        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("P_REF_CURSOR");

        return rows.stream().map(row -> {
            MedicalPoliciesDto dto = new MedicalPoliciesDto();

            Object idObj = row.get("ICREQUESTREFNO");
            if (idObj instanceof Number) {
                dto.setICREQUESTREFNO(((Number) idObj).longValue());
            } else if (idObj != null) {
                try {
                    dto.setICREQUESTREFNO(Long.parseLong(idObj.toString()));
                } catch (NumberFormatException e) {
                    dto.setICREQUESTREFNO(null);
                }
            }

            // Convert Strings safely
            dto.setUSERNAME(row.get("USERNAME") != null ? row.get("USERNAME").toString() : null);
            dto.setPASSWORD(row.get("PASSWORD") != null ? row.get("PASSWORD").toString() : null);
            dto.setINSURANCECOMPANYCODE(row.get("INSURANCECOMPANYCODE") != null ? row.get("INSURANCECOMPANYCODE").toString() : null);
            dto.setPOLICYNUMBER(row.get("POLICYNUMBER") != null ? row.get("POLICYNUMBER").toString() : null);

            // Convert Dates safely
            dto.setPOLICYISSUEDATE(convertToDate(row.get("POLICYISSUEDATE")));
            dto.setPOLICYSTARTDATE(convertToDate(row.get("POLICYSTARTDATE")));
            dto.setPOLICYEXPIRYDATE(convertToDate(row.get("POLICYEXPIRYDATE")));

            // Convert Long policy types safely
            dto.setPOLICYTYPE(convertToLong(row.get("POLICYTYPE")));
            dto.setPOLICYOWNERTYPE(convertToLong(row.get("POLICYOWNERTYPE")));

            // More Strings
            dto.setPOLICYOWNERNAME(row.get("POLICYOWNERNAME") != null ? row.get("POLICYOWNERNAME").toString() : null);
            dto.setPOLICYOWNERID(row.get("POLICYOWNERID") != null ? row.get("POLICYOWNERID").toString() : null);
            dto.setPLANNAME(row.get("PLANNAME") != null ? row.get("PLANNAME").toString() : null);
            dto.setOPERATIONTYPE(row.get("OPERATIONTYPE") != null ? row.get("OPERATIONTYPE").toString() : null);
            dto.setPOSTINGSTATUS(row.get("POSTINGSTATUS") != null ? row.get("POSTINGSTATUS").toString() : null);

            return dto;
        }).toList();
    }

    public List<MedicalMembersDto> getMedicalMembers
            (Long mpdPlcId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICAL_ICP")
                .withProcedureName("GET_MEDICAL_MEMBERS")
                .declareParameters(
                        new SqlParameter("P_MPD_PLC_ID", Types.NUMERIC),
                        new SqlOutParameter("P_REF_CURSOR", OracleTypes.CURSOR, new ColumnMapRowMapper())
                );

        Map<String, Object> result = jdbcCall.execute(
                new MapSqlParameterSource().addValue("P_MPD_PLC_ID", mpdPlcId));

        List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("P_REF_CURSOR");

        return rows.stream().map(row -> {
            MedicalMembersDto dto = new MedicalMembersDto();
            // Safe Long conversion for JICID
            dto.setJICID(convertToLong(row.get("JICID")));

            // Safe Long conversion for V_ICREQUESTREFNO
            dto.setV_ICREQUESTREFNO(convertToLong(row.get("ICREQUESTREFNO")));

            // Strings
            dto.setUSERNAME(getString(row, "USERNAME"));
            dto.setPASSWORD(getString(row, "PASSWORD"));
            dto.setINSURANCECOMPANYCODE(getString(row, "INSURANCECOMPANYCODE"));
            dto.setPOLICYCREATIONREFNO(getString(row, "POLICYCREATIONREFNO"));
            dto.setPOLICYNUMBER(getString(row, "POLICYNUMBER"));
            dto.setMEMBERREFNO(getString(row, "MEMBERREFNO"));
            dto.setUNIFIEDNO(getString(row, "UNIFIEDNO"));
            dto.setEMIRATESIDNO(getString(row, "EMIRATESIDNO"));
            dto.setVISAFILENO(getString(row, "VISAFILENO"));
            dto.setBIRTHCERTNO(getString(row, "BIRTHCERTNO"));
            dto.setPASSPORTNO(getString(row, "PASSPORTNO"));
            dto.setNATIONALITYCODE(getString(row, "NATIONALITYCODE"));
            dto.setFULLNAMEEN(getString(row, "FULLNAMEEN"));
            dto.setFIRSTNAMEEN(getString(row, "FIRSTNAMEEN"));
            dto.setMIDDLENAMEEN(getString(row, "MIDDLENAMEEN"));
            dto.setLASTNAMEEN(getString(row, "LASTNAMEEN"));
            dto.setFULLNAMEAR(getString(row, "FULLNAMEAR"));
            dto.setFIRSTNAMEAR(getString(row, "FIRSTNAMEAR"));
            dto.setMIDDLENAMEAR(getString(row, "MIDDLENAMEAR"));
            dto.setLASTNAMEAR(getString(row, "LASTNAMEAR"));
            dto.setSPONSORIDNO(getString(row, "SPONSORIDNO"));
            dto.setSPONSORIDTYPE(getString(row, "SPONSORIDTYPE"));
            dto.setMEMBERSHIPCARDNO(getString(row, "MEMBERSHIPCARDNO"));
            dto.setCLASSNAME(getString(row, "CLASSNAME"));
            dto.setOCCUPATIONDESC(getString(row, "OCCUPATIONDESC"));
            dto.setEMIRATESOFVISACODE(getString(row, "EMIRATESOFVISACODE"));
            dto.setEMIRATESOFLIVINGCODE(getString(row, "EMIRATESOFLIVINGCODE"));
            dto.setPOSTINGSTATUS(getString(row, "POSTINGSTATUS"));

            // Dates
            dto.setENROLMENTISSUEDATE(convertToDate(row.get("ENROLMENTISSUEDATE")));
            dto.setENROLMENTSTARTDATE(convertToDate(row.get("ENROLMENTSTARTDATE")));
            dto.setDATEOFBIRTH(convertToDate(row.get("DATEOFBIRTH")));

            // Longs
            dto.setGENDER(convertToLong(row.get("GENDER")));
            dto.setMARITALSTATUS(convertToLong(row.get("MARITALSTATUS")));
            dto.setRELATIONWITHSPONSOR(convertToLong(row.get("RELATIONWITHSPONSOR")));
            dto.setMEMBERTYPE(convertToLong(row.get("MEMBERTYPE")));
            return dto;
        }).toList();
    }

    private Date convertToDate(Object obj) {
        if (obj == null) return null;
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Timestamp) {
            return new Date(((Timestamp) obj).getTime());
        }
        // Could parse String here if needed, e.g. using SimpleDateFormat
        return null;
    }

    private Long convertToLong(Object obj) {
        if (obj == null) return null;
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        try {
            return Long.parseLong(obj.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static String getString(Map<String, Object> row, String key) {
        Object val = row.get(key);
        return val != null ? val.toString() : null;
    }

}
