package com.jicjo.apis.controller.general;

import com.jicjo.apis.service.general.GclFreezedClaimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/general")
public class ClaimsController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private GclFreezedClaimsService gclFreezedClaimsService;

        @GetMapping("/getFreezedLog")
    public ResponseEntity<?> getFreezedLog() {
        return ResponseEntity.ok().body(gclFreezedClaimsService.getFreezedLog());
    }
}
