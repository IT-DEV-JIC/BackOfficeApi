package com.jicjo.apis.service.core;

import com.jicjo.apis.dto.general.GclFreezedClaimsDto;
import com.jicjo.apis.repository.general.GclFreesedClaimsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelExportService implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final GclFreesedClaimsRepository repository;

    public byte[] exportToExcel() throws Exception {

        List<GclFreezedClaimsDto> data = repository.getFreezedLog();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Kroka Report");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("KROKA_NO");
        header.createCell(1).setCellValue("SEGMENT_CODE");
        header.createCell(2).setCellValue("REGISTRATION_DATE");

        int rowNum = 1;
        for (GclFreezedClaimsDto rowData : data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowData.getKROKANO());
            row.createCell(1).setCellValue(rowData.getSEGMENTCODE());
            row.createCell(2).setCellValue(
                    rowData.getREGISTRATIONDATE() != null ?
                            rowData.getREGISTRATIONDATE().toString() : ""
            );
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
