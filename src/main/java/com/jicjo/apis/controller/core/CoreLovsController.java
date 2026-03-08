package com.jicjo.apis.controller.core;


import com.jicjo.apis.dto.core.CoreLovs;
import com.jicjo.apis.service.core.CoreLovsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/core")
public class CoreLovsController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private CoreLovsService coreLovsService;

    @GetMapping("/getCstByclntName")
    public ResponseEntity<List<CoreLovs>> getCstByclntName(@RequestParam String clntName) {
        return ResponseEntity.ok(coreLovsService.getCstByclntName(clntName));
    }

    @GetMapping("/findBrnByclntName")
    public ResponseEntity<List<CoreLovs>> findBrnByclntName(@RequestParam String clntName) {
        return ResponseEntity.ok(coreLovsService.findBrnByclntName(clntName));
    }

    @GetMapping("/findPstApplicationWordingsById")
    public ResponseEntity<List<CoreLovs>> findPstApplicationWordingsById(@RequestParam(required = false) Long pstApwId) {
        return ResponseEntity.ok(coreLovsService.findPstApplicationWordingsById(pstApwId));
    }

    @GetMapping("/findMpdPolicies")
    public ResponseEntity<List<CoreLovs>> findMpdPolicies() {
        return ResponseEntity.ok(coreLovsService.findMpdPolicies());
    }
}
