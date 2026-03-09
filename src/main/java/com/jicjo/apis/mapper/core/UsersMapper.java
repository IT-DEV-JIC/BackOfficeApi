package com.jicjo.apis.mapper.core;

import com.jicjo.apis.dto.core.ApplicationScreensDto;
import com.jicjo.apis.dto.core.UsersDto;
import com.jicjo.apis.model.core.ApplicationScreens;
import com.jicjo.apis.model.core.Users;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class UsersMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static UsersDto toUsersDto(Users users) {
        return new UsersDto(
                users.getUserName(),
                users.getPassword(),
                users.getDescription(),
                users.getType(),
                users.getActive(),
                users.getEmail(),
                users.getMobile(),
                users.getPhone(),
                users.getFullEnName(),
                users.getFirstEnName(),
                users.getSecondEnName(),
                users.getThirdEnName(),
                users.getFamilyEnName(),
                users.getFullArFullName(),
                users.getFirstArName(),
                users.getSecondArName(),
                users.getThirdArName(),
                users.getFamilyArName(),
                users.getGender(),
                users.getDob(),
                users.getMaritalStatus(),
                users.getReligious(),
                users.getHomeTownCountry(),
                users.getHomeTownCity(),
                users.getHomeTownPlace(),
                users.getBirthCountry(),
                users.getBirthCity(),
                users.getBirthPlace(),
                users.getCountry(),
                users.getCity(),
                users.getPlace(),
                users.getNationality(),
                users.getEnAddress(),
                users.getArAddress(),
                users.getEmployeeNo(),
                users.getNationalNo(),
                users.getForeignNo(),
                users.getCardId(),
                users.getCivilRecordNo(),
                users.getMajor(),
                users.getPreferredLang(),
                users.getValidateKey(),
                users.getPic2Path(),
                users.getPic3Path(),
                users.getCreatedBy(),
                users.getCreatedOn(),
                users.getUpdatedBy(),
                users.getUpdatedOn(),
                users.getDeptNo(),
                users.getClntName(),
                users.getPrinter1(),
                users.getPrinter2(),
                users.getLongitude(),
                users.getLatitude(),
                users.getUserType(),
                users.getShopName(),
                users.getOwnerName(),
                users.getPinCode(),
                users.getCashBoxFlag(),
                users.getFirebaseToken(),
                users.getTargetGender(),
                users.getPic1Path(),
                users.getPswdExpiryDate()
        );
    }

    public static Users toUsers(UsersDto usersDto) {
        return new Users(
                usersDto.getUserName(),
                usersDto.getPassword(),
                usersDto.getDescription(),
                usersDto.getType(),
                usersDto.getActive(),
                usersDto.getEmail(),
                usersDto.getMobile(),
                usersDto.getPhone(),
                usersDto.getFullEnName(),
                usersDto.getFirstEnName(),
                usersDto.getSecondEnName(),
                usersDto.getThirdEnName(),
                usersDto.getFamilyEnName(),
                usersDto.getFullArFullName(),
                usersDto.getFirstArName(),
                usersDto.getSecondArName(),
                usersDto.getThirdArName(),
                usersDto.getFamilyArName(),
                usersDto.getGender(),
                usersDto.getDob(),
                usersDto.getMaritalStatus(),
                usersDto.getReligious(),
                usersDto.getHomeTownCountry(),
                usersDto.getHomeTownCity(),
                usersDto.getHomeTownPlace(),
                usersDto.getBirthCountry(),
                usersDto.getBirthCity(),
                usersDto.getBirthPlace(),
                usersDto.getCountry(),
                usersDto.getCity(),
                usersDto.getPlace(),
                usersDto.getNationality(),
                usersDto.getEnAddress(),
                usersDto.getArAddress(),
                usersDto.getEmployeeNo(),
                usersDto.getNationalNo(),
                usersDto.getForeignNo(),
                usersDto.getCardId(),
                usersDto.getCivilRecordNo(),
                usersDto.getMajor(),
                usersDto.getPreferredLang(),
                usersDto.getValidateKey(),
                usersDto.getPic2Path(),
                usersDto.getPic3Path(),
                usersDto.getCreatedBy(),
                usersDto.getCreatedOn(),
                usersDto.getUpdatedBy(),
                usersDto.getUpdatedOn(),
                usersDto.getDeptNo(),
                usersDto.getClntName(),
                usersDto.getPrinter1(),
                usersDto.getPrinter2(),
                usersDto.getLongitude(),
                usersDto.getLatitude(),
                usersDto.getUserType(),
                usersDto.getShopName(),
                usersDto.getOwnerName(),
                usersDto.getPinCode(),
                usersDto.getCashBoxFlag(),
                usersDto.getFirebaseToken(),
                usersDto.getTargetGender(),
                usersDto.getPic1Path(),
                usersDto.getPswdExpiryDate()
        );
    }

    public static List<UsersDto> toUsersDtoList(List<Users> users) {
        return users.stream()
                .map(UsersMapper::toUsersDto)
                .collect(Collectors.toList());
    }
}
