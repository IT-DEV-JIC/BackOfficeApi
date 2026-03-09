package com.jicjo.apis.model.core;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = {"USR_NAME"})})
public class Users implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USR_NAME")
    private String userName;
    @Column(name = "USR_PSWD")
    private String password;
    @Column(name = "USR_DESCRIPTION")
    private String description;
    @Column(name = "USR_TYPE")
    private Integer type;
    @Column(name = "USR_ACTIVE")
    private Integer active;
    @Column(name = "USR_EMAIL")
    private String email;
    @Column(name = "USR_MOBILE")
    private String mobile;
    @Column(name = "USR_PHONE")
    private String phone;
    @Column(name = "USR_EN_FULL_NAME")
    private String fullEnName;
    @Column(name = "USR_EN_FIRST_NAME")
    private String firstEnName;
    @Column(name = "USR_EN_SECOND_NAME")
    private String secondEnName;
    @Column(name = "USR_EN_THIRD_NAME")
    private String thirdEnName;
    @Column(name = "USR_EN_FAMILY_NAME")
    private String familyEnName;
    @Column(name = "USR_AR_FULL_NAME")
    private String fullArFullName;
    @Column(name = "USR_AR_FIRST_NAME")
    private String firstArName;
    @Column(name = "USR_AR_SECOND_NAME")
    private String secondArName;
    @Column(name = "USR_AR_THIRD_NAME")
    private String thirdArName;
    @Column(name = "USR_AR_FAMILY_NAME")
    private String familyArName;
    @Column(name = "USR_GENDER")
    private String gender;
    @Column(name = "USR_DOB")
    private Date dob;
    @Column(name = "USR_MARITAL_STATUS")
    private Integer maritalStatus;
    @Column(name = "USR_RELIGIOUSE")
    private Integer religious;
    @Column(name = "USR_HOME_TOWN_COUNTRY")
    private Integer homeTownCountry;
    @Column(name = "USR_HOME_TOWN_CITY")
    private Integer homeTownCity;
    @Column(name = "USR_HOME_TOWN_PLACE")
    private Integer homeTownPlace;
    @Column(name = "USR_BIRTH_COUNTRY")
    private Integer birthCountry;
    @Column(name = "USR_BIRTH_CITY")
    private Integer birthCity;
    @Column(name = "USR_BIRTH_PLACE")
    private Integer birthPlace;
    @Column(name = "USR_COUNTRY")
    private Integer country;
    @Column(name = "USR_CITY")
    private Integer city;
    @Column(name = "USR_PLACE")
    private Integer place;
    @Column(name = "USR_NATIONALITY")
    private Integer nationality;
    @Column(name = "USR_EN_ADDRESS")
    private String enAddress;
    @Column(name = "USR_AR_ADDRESS")
    private String arAddress;
    @Column(name = "USR_EMPLOYEE_NO")
    private Long employeeNo;
    @Column(name = "USR_NATIONAL_NO")
    private Long nationalNo;
    @Column(name = "USR_FOREIGN_NO")
    private Long foreignNo;
    @Column(name = "USR_CARD_ID")
    private String cardId;
    @Column(name = "USR_CIVIL_RECORD_NO")
    private String civilRecordNo;
    @Column(name = "USR_MAJOR")
    private Integer major;
    @Column(name = "USR_PREFERED_LANG")
    private String preferredLang;
    @Column(name = "USR_VALIDATE_KEY")
    private String validateKey;
    @Column(name = "USR_PIC2_PATH")
    private String pic2Path;
    @Column(name = "USR_PIC3_PATH")
    private String pic3Path;
    @Column(name = "USR_CREATED_BY")
    private String createdBy;
    @Column(name = "USR_CREATED_ON")
    private Date createdOn;
    @Column(name = "USR_UPDATED_BY")
    private String updatedBy;
    @Column(name = "USR_UPDATED_ON")
    private Date updatedOn;
    @Column(name = "USR_DEPT_NO")
    private Integer deptNo;
    @Column(name = "CLNT_NAME")
    private String clntName;
    @Column(name = "PRINTER_1")
    private String printer1;
    @Column(name = "PRINTER_2")
    private String printer2;
    @Column(name = "USR_LONGITUDE")
    private String longitude;
    @Column(name = "USR_LATITUDE")
    private String latitude;
    @Column(name = "USR_USER_TYPE")
    private Integer userType;
    @Column(name = "USR_SHOP_NAME")
    private String shopName;
    @Column(name = "USR_SHOP_OWNER_NAME")
    private String ownerName;
    @Column(name = "USR_PINCODE")
    private String pinCode;
    @Column(name = "USR_CASH_BOX_FLAG")
    private Integer cashBoxFlag;
    @Column(name = "USR_FIREBASE_TOKEN")
    private String firebaseToken;
    @Column(name = "USR_TARGET_GENDER")
    private Integer targetGender;
    @Column(name = "USR_PIC1_PATH")
    private String pic1Path;
    @Column(name = "USR_PSWD_EXPIRY_DATE")
    private Date pswdExpiryDate;

    public Users(String userName, String password, String description, Integer type, Integer active, String email, String mobile, String phone, String fullEnName, String firstEnName, String secondEnName, String thirdEnName, String familyEnName, String fullArFullName, String firstArName, String secondArName, String thirdArName, String familyArName, String gender, Date dob, Integer maritalStatus, Integer religious, Integer homeTownCountry, Integer homeTownCity, Integer homeTownPlace, Integer birthCountry, Integer birthCity, Integer birthPlace, Integer country, Integer city, Integer place, Integer nationality, String enAddress, String arAddress, Long employeeNo, Long nationalNo, Long foreignNo, String cardId, String civilRecordNo, Integer major, String preferredLang, String validateKey, String pic2Path, String pic3Path, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Integer deptNo, String clntName, String printer1, String printer2, String longitude, String latitude, Integer userType, String shopName, String ownerName, String pinCode, Integer cashBoxFlag, String firebaseToken, Integer targetGender, String pic1Path,Date pswdExpiryDate) {
        this.userName = userName;
        this.password = password;
        this.description = description;
        this.type = type;
        this.active = active;
        this.email = email;
        this.mobile = mobile;
        this.phone = phone;
        this.fullEnName = fullEnName;
        this.firstEnName = firstEnName;
        this.secondEnName = secondEnName;
        this.thirdEnName = thirdEnName;
        this.familyEnName = familyEnName;
        this.fullArFullName = fullArFullName;
        this.firstArName = firstArName;
        this.secondArName = secondArName;
        this.thirdArName = thirdArName;
        this.familyArName = familyArName;
        this.gender = gender;
        this.dob = dob;
        this.maritalStatus = maritalStatus;
        this.religious = religious;
        this.homeTownCountry = homeTownCountry;
        this.homeTownCity = homeTownCity;
        this.homeTownPlace = homeTownPlace;
        this.birthCountry = birthCountry;
        this.birthCity = birthCity;
        this.birthPlace = birthPlace;
        this.country = country;
        this.city = city;
        this.place = place;
        this.nationality = nationality;
        this.enAddress = enAddress;
        this.arAddress = arAddress;
        this.employeeNo = employeeNo;
        this.nationalNo = nationalNo;
        this.foreignNo = foreignNo;
        this.cardId = cardId;
        this.civilRecordNo = civilRecordNo;
        this.major = major;
        this.preferredLang = preferredLang;
        this.validateKey = validateKey;
        this.pic2Path = pic2Path;
        this.pic3Path = pic3Path;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.deptNo = deptNo;
        this.clntName = clntName;
        this.printer1 = printer1;
        this.printer2 = printer2;
        this.longitude = longitude;
        this.latitude = latitude;
        this.userType = userType;
        this.shopName = shopName;
        this.ownerName = ownerName;
        this.pinCode = pinCode;
        this.cashBoxFlag = cashBoxFlag;
        this.firebaseToken = firebaseToken;
        this.targetGender = targetGender;
        this.pic1Path = pic1Path;
        this.pswdExpiryDate = pswdExpiryDate;
    }

    /* Users Login Logout Logs Foreign Key */
    @OneToMany(mappedBy = "usersLogs",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<UsersLoginLogout> UsersLoginLogoutList = new ArrayList<>();

    /* Create By Foreign Key */
    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Applications> applicationsCreatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<ApplicationScreens> applicationScreensCreatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Clients> clientsCreatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<ClientScreen> clientScreenCreatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<GroupMembers> groupMembersCreatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Groups> groupsCreatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<GroupScreens> groupScreensCreatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<SysCodes> sysCodesCreatedByList = new ArrayList<>();

    /* Updated By Foreign Key */
    @OneToMany(mappedBy = "updatedBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Applications> applicationsUpdatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<ApplicationScreens> applicationScreensUpdatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Clients> clientsUpdatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<ClientScreen> clientScreenUpdatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<GroupMembers> groupMembersUpdatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Groups> groupsUpdatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<GroupScreens> groupScreensUpdatedByList = new ArrayList<>();

    @OneToMany(mappedBy = "updatedBy",cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<SysCodes> sysCodesUpdateddByList = new ArrayList<>();

}
