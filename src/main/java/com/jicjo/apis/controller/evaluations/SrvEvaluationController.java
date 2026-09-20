package com.jicjo.apis.controller.evaluations;

import com.jicjo.apis.dto.evaluations.SrvEvaluationDto;
import com.jicjo.apis.service.evaluations.SrvEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/evaluation")

public class SrvEvaluationController {
    @Autowired
    private SrvEvaluationService srvEvaluationService;


    @PostMapping("/addSrvEvaluation")
    public ResponseEntity<SrvEvaluationDto> addSrvEvaluation(@RequestBody SrvEvaluationDto srvEvaluationsDto) {
        return ResponseEntity.ok(srvEvaluationService.addSrvEvaluation(srvEvaluationsDto));
    }

    @PutMapping("/updateSrvEvaluation")
    public ResponseEntity<SrvEvaluationDto> updateSrvEvaluation(@RequestBody SrvEvaluationDto srvEvaluationDto) {
        return ResponseEntity.ok(srvEvaluationService.updateSrvEvaluation(srvEvaluationDto));
    }
    @DeleteMapping("/deleteSrvEvaluation")
    public ResponseEntity<String> deleteSrvEvaluation(@RequestParam long srvEvaluationId) {
        srvEvaluationService.deleteSrvEvaluation(srvEvaluationId);
        return ResponseEntity.ok("OK");
    }
}