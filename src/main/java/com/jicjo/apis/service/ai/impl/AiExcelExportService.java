
package com.jicjo.apis.service.ai.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Map;

@Service
public class AiExcelExportService {

    public byte[] export(List<Map<String, Object>> rows) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream output =
                     new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("AI Report");

            if (rows == null || rows.isEmpty()) {
                Row row = sheet.createRow(0);
                row.createCell(0).setCellValue("No data");
            } else {

                List<String> columns = rows.stream()
                        .flatMap(row -> row.keySet().stream())
                        .distinct()
                        .toList();

                CellStyle headerStyle = workbook.createCellStyle();

                Font headerFont = workbook.createFont();
                headerFont.setBold(true);

                headerStyle.setFont(headerFont);

                Row headerRow = sheet.createRow(0);

                for (int i = 0; i < columns.size(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns.get(i));
                    cell.setCellStyle(headerStyle);
                }

                int rowIndex = 1;

                for (Map<String, Object> data : rows) {

                    Row excelRow = sheet.createRow(rowIndex++);

                    for (int i = 0; i < columns.size(); i++) {

                        Object value = data.get(columns.get(i));

                        Cell cell = excelRow.createCell(i);

                        if (value == null) {
                            cell.setBlank();
                        } else if (value instanceof Number number) {
                            cell.setCellValue(number.doubleValue());
                        } else if (value instanceof Boolean bool) {
                            cell.setCellValue(bool);
                        } else {
                            cell.setCellValue(String.valueOf(value));
                        }
                    }
                }

                for (int i = 0; i < columns.size(); i++) {
                    sheet.autoSizeColumn(i);
                }
            }

            workbook.write(output);

            return output.toByteArray();

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to generate Excel report", e
            );
        }
    }
}
