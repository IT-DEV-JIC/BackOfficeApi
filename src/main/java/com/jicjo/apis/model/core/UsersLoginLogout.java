package com.jicjo.apis.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS_LOGIN_LOG", uniqueConstraints = {@UniqueConstraint(columnNames = {"USRL_ID"})})
public class UsersLoginLogout implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName  = "USERS_LOGIN_LOG_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_GEN")
    @Column(name = "USRL_ID")
    private Long usrlId;
    @Column(name = "USR_NAME")
    private String usrName;
    @Column(name = "USRL_LOGIN_TIME")
    private Date usrLoginTime;
    @Column(name = "USRL_LOGOFF_TIME")
    private Date usrLogoffTime;
    @Column(name = "USRL_ESTIMATION_TIME")
    private String usrEstimationTime;
    @Column(name = "USRL_SERVER_IP_ADDRESS")
    private String usrServerIpAddress;
    @Column(name = "USRL_PUPLIC_IP_ADDRESS")
    private String usrPuplicIpAddress;
    @Column(name = "USRL_MACHINE_NAME")
    private String usrMachineName;
    @Column(name = "USRL_BROWSER_NAME")
    private String usrBrowserName;
    @Column(name = "USRL_COUNTRY_CODE")
    private String usrCountryCode;
    @Column(name = "USRL_COUNTRY_NAME")
    private String usrCountryName;
    @Column(name = "USRL_CITY_NAME")
    private String usrCityName;
    @Column(name = "USRL_REGION_CODE")
    private String usrRegionCode;
    @Column(name = "USRL_REGION_NAME")
    private String usrRegionName;
    @Column(name = "USRL_POSTAL_CODE")
    private String usrPostalCode;
    @Column(name = "USRL_LONGITUDE")
    private String usrLongitude;
    @Column(name = "USRL_LATITUDE")
    private String usrLatitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="USR_NAME",referencedColumnName="USR_NAME",nullable=false,insertable = false, updatable = false)
    private Users usersLogs;

    public UsersLoginLogout(Long usrlId, String usrName, Date usrLoginTime, Date usrLogoffTime, String usrEstimationTime, String usrServerIpAddress, String usrPuplicIpAddress, String usrMachineName, String usrBrowserName, String usrCountryCode, String usrCountryName, String usrCityName, String usrRegionCode, String usrRegionName, String usrPostalCode, String usrLongitude, String usrLatitude) {
        this.usrlId = usrlId;
        this.usrName = usrName;
        this.usrLoginTime = usrLoginTime;
        this.usrLogoffTime = usrLogoffTime;
        this.usrEstimationTime = usrEstimationTime;
        this.usrServerIpAddress = usrServerIpAddress;
        this.usrPuplicIpAddress = usrPuplicIpAddress;
        this.usrMachineName = usrMachineName;
        this.usrBrowserName = usrBrowserName;
        this.usrCountryCode = usrCountryCode;
        this.usrCountryName = usrCountryName;
        this.usrCityName = usrCityName;
        this.usrRegionCode = usrRegionCode;
        this.usrRegionName = usrRegionName;
        this.usrPostalCode = usrPostalCode;
        this.usrLongitude = usrLongitude;
        this.usrLatitude = usrLatitude;
    }
}
