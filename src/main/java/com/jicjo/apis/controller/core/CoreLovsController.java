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

    @GetMapping("/findMpdPolicies")
    public ResponseEntity<List<CoreLovs>> findMpdPolicies() {
        return ResponseEntity.ok(coreLovsService.findMpdPolicies());
    }

    @GetMapping("/findUsersByclntName")
    public ResponseEntity<List<CoreLovs>> findUsersByclntName(@RequestParam String clntName, @RequestParam String userName) {
        return ResponseEntity.ok(coreLovsService.findUsersByclntName(clntName,userName));
    }
}
