package com.jicjo.apis.controller.evaluations;

import com.jicjo.apis.dto.evaluations.SrvEvaluationDetailsDto;
import com.jicjo.apis.service.evaluations.SrvEvaluationDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/evaluation")

public class SrvEvaluationDetailsController {
    @Autowired
    private SrvEvaluationDetailsService srvEvaluationDetailsService;


    @PostMapping("/addSrvEvaluationDetails")
    public ResponseEntity<SrvEvaluationDetailsDto> addSrvEvaluationDetails(@RequestBody SrvEvaluationDetailsDto srvEvaluationDetailsDto) {
        return ResponseEntity.ok(srvEvaluationDetailsService.addSrvEvaluationDetails(srvEvaluationDetailsDto));
    }

    @PutMapping("/updateSrvEvaluationDetails")
    public ResponseEntity<SrvEvaluationDetailsDto> updateSrvEvaluationDetails(@RequestBody SrvEvaluationDetailsDto srvEvaluationDetailsDto) {
        return ResponseEntity.ok(srvEvaluationDetailsService.updateSrvEvaluationDetails(srvEvaluationDetailsDto));
    }
    @DeleteMapping("/deleteSrvEvaluationDetails")
    public ResponseEntity<String> deleteSrvEvaluationDetails(@RequestParam long srvEvaluationId) {
        srvEvaluationDetailsService.deleteSrvEvaluationDetails(srvEvaluationId);
        return ResponseEntity.ok("OK");
    }
}
