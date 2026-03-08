package com.jicjo.apis.dto.core;

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
public class UsersDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;
    private String description;
    private Integer type;
    private Integer active;
    private String email;
    private String mobile;
    private String phone;
    private String fullEnName;
    private String firstEnName;
    private String secondEnName;
    private String thirdEnName;
    private String familyEnName;
    private String fullArFullName;
    private String firstArName;
    private String secondArName;
    private String thirdArName;
    private String familyArName;
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private Integer maritalStatus;
    private Integer religious;
    private Integer homeTownCountry;
    private Integer homeTownCity;
    private Integer homeTownPlace;
    private Integer birthCountry;
    private Integer birthCity;
    private Integer birthPlace;
    private Integer country;
    private Integer city;
    private Integer place;
    private Integer nationality;
    private String enAddress;
    private String arAddress;
    private Long employeeNo;
    private Long nationalNo;
    private Long foreignNo;
    private String cardId;
    private String civilRecordNo;
    private Integer major;
    private String preferredLang;
    private String validateKey;
    private String pic2Path;
    private String pic3Path;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdOn;
    private String updatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedOn;
    private Integer deptNo;
    private String clntName;
    private String printer1;
    private String printer2;
    private String longitude;
    private String latitude;
    private Integer userType;
    private String shopName;
    private String ownerName;
    private String pinCode;
    private Integer cashBoxFlag;
    private String firebaseToken;
    private Integer targetGender;
    private String pic1Path;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pswdExpiryDate;
}
