package com.jicjo.apis.controller.general;



import com.jicjo.apis.service.general.GclEkrookaClaimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/general")
public class EkrookaClaimsController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private GclEkrookaClaimsService ekrookaClaimsService;

    @GetMapping("/getGclEkrookaClaims")
    public ResponseEntity<?> getGclEkrookaClaims(@RequestParam Date accidentDate) {
        return ResponseEntity.ok().body(ekrookaClaimsService.getGclEkrookaClaims(accidentDate));
    }
}
