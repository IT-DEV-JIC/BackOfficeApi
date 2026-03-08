package com.jicjo.apis.dto.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersLoginLogoutDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long usrlId;
    private String usrName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date usrLoginTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date usrLogoffTime;
    private String usrEstimationTime;
    private String usrServerIpAddress;
    private String usrPuplicIpAddress;
    private String usrMachineName;
    private String usrBrowserName;
    private String usrCountryCode;
    private String usrCountryName;
    private String usrCityName;
    private String usrRegionCode;
    private String usrRegionName;
    private String usrPostalCode;
    private String usrLongitude;
    private String usrLatitude;
}
