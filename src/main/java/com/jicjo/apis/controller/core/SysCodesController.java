package com.jicjo.apis.controller.core;

import com.jicjo.apis.dto.core.SysCodesDto;
import com.jicjo.apis.service.core.SysCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/core")
public class SysCodesController implements Serializable {

    @Autowired
    private SysCodesService sysCodesService;

    @GetMapping("/getSysCodes")
    public ResponseEntity<List<SysCodesDto>> getSysCodes(@RequestParam(required = false) Long scType, @RequestParam(required = false) Long scCode) {
        //return new ResponseEntity<>(sysCodesService.getSysCodes(scType, scCode), HttpStatus.FOUND);
        return ResponseEntity.ok(sysCodesService.getSysCodes(scType, scCode));
    }
}
