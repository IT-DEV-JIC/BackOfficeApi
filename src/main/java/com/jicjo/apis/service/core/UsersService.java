package com.jicjo.apis.service.core;

import com.jicjo.apis.dto.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
public interface UsersService extends Serializable {
    void registerUser(UsersDto usersDto);
    Long addUserLog(UsersLoginLogoutDto usersLoginLogoutDto);
    void updateUserLog(Long userId, Date usrLogoffTime, String usrEstimationTime);
    List<ApplicationsDto> loadApplications(String userName, String clntName);
    List<ApplicationScreensDto> loadApplicationScreens(String userName, String clntName, Long appId);
    UsersInfoDto loadUsers(String userName);
    List<UsersDto> getAllUsersInfo(String userName, String clntName);
    void updateUsers(UsersDto usersDto);
    String resetPassword(String userName);
    String changePassword(String userName,String newPassword);
    void lockUser(String userName);
}
