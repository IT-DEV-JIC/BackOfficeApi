package com.jicjo.apis.controller.general;



import com.jicjo.apis.service.general.AccidentHeatmapService;
import com.jicjo.apis.service.general.GclEkrookaClaimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    private GclEkrookaClaimsService gclEkrookaClaimsService;

    @Autowired
    private AccidentHeatmapService accidentHeatmapService;

    @GetMapping("/getGclEkrookaClaims")
    public ResponseEntity<?> getGclEkrookaClaims(@RequestParam Date accidentDate) {
        return ResponseEntity.ok().body(gclEkrookaClaimsService.getGclEkrookaClaims(accidentDate));
    }

    @GetMapping("/getAccidentHeatmap")
    public ResponseEntity<?> getAccidentHeatmap(@RequestParam Date date) {
        return ResponseEntity.ok().body(accidentHeatmapService.getAccidentHeatmapDto(date));
    }
}
