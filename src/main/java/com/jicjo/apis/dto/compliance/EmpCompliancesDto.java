package com.jicjo.apis.dto.compliance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpCompliancesDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long empCompliancesId;
    private String empCompliancesTitle;
    private String empCompliancesBody;
    private String empCompliancesIp;
    private String empCompliancesHost;
    private String empCompliancesWindowsUser;
    private String empCompliancesRequestUri;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date empCompliancesCompliancesOn;
}

