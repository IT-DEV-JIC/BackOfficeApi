package com.jicjo.apis.controller.core;

import com.jicjo.apis.service.core.ExcelExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/core")
public class ReportController implements Serializable {
    private final ExcelExportService service;

    public ReportController(ExcelExportService service) {
        this.service = service;
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() throws Exception {

        byte[] excelData = service.exportToExcel();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=kroka-report.xlsx")
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .body(excelData);
    }
}
