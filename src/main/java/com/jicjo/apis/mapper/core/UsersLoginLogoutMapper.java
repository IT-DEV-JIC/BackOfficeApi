package com.jicjo.apis.mapper.core;

import com.jicjo.apis.dto.core.UsersLoginLogoutDto;
import com.jicjo.apis.model.core.UsersLoginLogout;

import java.io.Serial;
import java.io.Serializable;

public class UsersLoginLogoutMapper implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static UsersLoginLogoutDto toUsersLoginLogoutDto(UsersLoginLogout usersLoginLogout) {
        return new UsersLoginLogoutDto(
                usersLoginLogout.getUsrlId(),
                usersLoginLogout.getUsrName(),
                usersLoginLogout.getUsrLoginTime(),
                usersLoginLogout.getUsrLogoffTime(),
                usersLoginLogout.getUsrEstimationTime(),
                usersLoginLogout.getUsrServerIpAddress(),
                usersLoginLogout.getUsrPuplicIpAddress(),
                usersLoginLogout.getUsrMachineName(),
                usersLoginLogout.getUsrBrowserName(),
                usersLoginLogout.getUsrCountryCode(),
                usersLoginLogout.getUsrCountryName(),
                usersLoginLogout.getUsrCityName(),
                usersLoginLogout.getUsrRegionCode(),
                usersLoginLogout.getUsrRegionName(),
                usersLoginLogout.getUsrPostalCode(),
                usersLoginLogout.getUsrLongitude(),
                usersLoginLogout.getUsrLatitude()
        );
    }

    public static UsersLoginLogout toUsersLoginLogout(UsersLoginLogoutDto usersLoginLogoutDto) {
        return new UsersLoginLogout(
                usersLoginLogoutDto.getUsrlId(),
                usersLoginLogoutDto.getUsrName(),
                usersLoginLogoutDto.getUsrLoginTime(),
                usersLoginLogoutDto.getUsrLogoffTime(),
                usersLoginLogoutDto.getUsrEstimationTime(),
                usersLoginLogoutDto.getUsrServerIpAddress(),
                usersLoginLogoutDto.getUsrPuplicIpAddress(),
                usersLoginLogoutDto.getUsrMachineName(),
                usersLoginLogoutDto.getUsrBrowserName(),
                usersLoginLogoutDto.getUsrCountryCode(),
                usersLoginLogoutDto.getUsrCountryName(),
                usersLoginLogoutDto.getUsrCityName(),
                usersLoginLogoutDto.getUsrRegionCode(),
                usersLoginLogoutDto.getUsrRegionName(),
                usersLoginLogoutDto.getUsrPostalCode(),
                usersLoginLogoutDto.getUsrLongitude(),
                usersLoginLogoutDto.getUsrLatitude()
        );
    }
}
