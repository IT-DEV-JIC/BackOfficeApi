package com.jicjo.apis.controller.icp;


import com.jicjo.apis.dto.core.CoreLovs;
import com.jicjo.apis.dto.icp.JicIcpConnectionDto;
import com.jicjo.apis.dto.icp.JicIcpMembersDto;
import com.jicjo.apis.dto.icp.MedicalMembersDto;
import com.jicjo.apis.dto.icp.MedicalPoliciesDto;
import com.jicjo.apis.service.icp.JicIcpConnectionService;
import com.jicjo.apis.service.icp.MedicalPoliciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/icp")
@RequiredArgsConstructor
public class MedicalPoliciesController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private MedicalPoliciesService medicalPoliciesService;

    @Autowired
    private JicIcpConnectionService jicIcpConnectionService;

    @GetMapping("/getMedicalPolicies")
    public ResponseEntity<List<MedicalPoliciesDto>> getCstByclntName(@RequestParam(required = false) Long mpdPlcId,
                                                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromIssueDate,
                                                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toIssueDate ) {

        return ResponseEntity.ok(medicalPoliciesService.getMedicalPolicies(mpdPlcId, fromIssueDate, toIssueDate));
    }

    @GetMapping("/getMedicalMembers")
    public ResponseEntity<List<MedicalMembersDto>> getMedicalMembers(@RequestParam Long mpdPlcId) {

        return ResponseEntity.ok(medicalPoliciesService.getMedicalMembers(mpdPlcId));
    }

    @PostMapping("/addJicIcpConnection")
    public ResponseEntity<JicIcpConnectionDto> addJicIcpConnection(@RequestBody JicIcpConnectionDto jicIcpConnectionDto) {
        return ResponseEntity.ok(jicIcpConnectionService.addJicIcpConnection(jicIcpConnectionDto));
    }

    @PostMapping("/addJicIcpMembers")
    public ResponseEntity<JicIcpMembersDto> addJicIcpMembers(@RequestBody JicIcpMembersDto jicIcpMembersDto) {
        return ResponseEntity.ok(jicIcpConnectionService.addJicIcpMembers(jicIcpMembersDto));
    }

    @PostMapping("/sendMedicalPolicies")
    public ResponseEntity<String> sendMedicalPolicies(@RequestParam Long mpdPlcId) {
        return ResponseEntity.ok(medicalPoliciesService.sendMedicalPolicies(mpdPlcId));
    }
}
