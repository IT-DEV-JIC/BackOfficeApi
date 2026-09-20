package com.jicjo.apis.controller.general;

import com.jicjo.apis.dto.general.*;
import com.jicjo.apis.repository.general.TahaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/general")
public class TahaController {

    @Autowired
    private TahaRepository tahaRepository;

    @GetMapping("/getExpectedRecoveryLastPayment")
    public ResponseEntity<List<ExpectedRecoveryLastPaymentDto>> getExpectedRecoveryLastPayment(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate
    ) {
        List<ExpectedRecoveryLastPaymentDto> results = tahaRepository.getExpectedRecoveryLastPayment(fromDate, toDate);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/getMotorComOsRecoveryLastRec")
    public ResponseEntity<List<MotorComOsRecoveryLastRec>> getMotorComOsRecoveryLastRec(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate
    ) {
        List<MotorComOsRecoveryLastRec> results = tahaRepository.getMotorComOsRecoveryLastRec(fromDate, toDate);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/getOsLatest")
    public ResponseEntity<List<OsLatest>> getOsLatest(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date asAtDate
    ) {
        List<OsLatest> results = tahaRepository.getOsLatest(asAtDate);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/getProduction")
    public ResponseEntity<List<ProductionDto>> getProduction(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate
    ) {
        List<ProductionDto> results = tahaRepository.getProduction(fromDate,toDate);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/getPaidRecoveryLatest")
    public ResponseEntity<List<PaidRecoveryLatestDto>> getPaidRecoveryLatest(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate
    ) {
        List<PaidRecoveryLatestDto> results = tahaRepository.getPaidRecoveryLatest(fromDate,toDate);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/getRepairPurchaseOrder")
    public ResponseEntity<List<RepairPurchaseOrderDto>> getRepairPurchaseOrder(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate
    ) {
        List<RepairPurchaseOrderDto> results = tahaRepository.getRepairPurchaseOrder(fromDate,toDate);
        return ResponseEntity.ok(results);
    }
}
