package com.jicjo.apis.service.core.impl;

import com.jicjo.apis.dto.core.*;
import com.jicjo.apis.mapper.core.ApplicationScreensMapper;
import com.jicjo.apis.mapper.core.ApplicationsMapper;
import com.jicjo.apis.mapper.core.UsersLoginLogoutMapper;
import com.jicjo.apis.mapper.core.UsersMapper;
import com.jicjo.apis.model.core.Users;
import com.jicjo.apis.model.core.UsersLoginLogout;
import com.jicjo.apis.repository.core.ApplicationScreensRepository;
import com.jicjo.apis.repository.core.ApplicationsRepository;
import com.jicjo.apis.repository.core.UsersLoginLogoutRepository;
import com.jicjo.apis.repository.core.UsersRepository;
import com.jicjo.apis.service.core.UsersService;
import com.jicjo.apis.utility.RandomGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersLoginLogoutRepository usersLoginLogoutRepository;

    @Autowired
    ApplicationsRepository applicationsRepository;

    @Autowired
    ApplicationScreensRepository applicationScreensRepository;

    @Override
    @Transactional
    public void registerUser(UsersDto usersDto) {
        if (usersRepository.findByUsername(usersDto.getUserName()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        Users users = UsersMapper.toUsers(usersDto);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
    }

    @Override
    @Transactional
    public Long addUserLog(UsersLoginLogoutDto usersLoginLogoutDto) {
        UsersLoginLogout loginLog = UsersLoginLogoutMapper.toUsersLoginLogout(usersLoginLogoutDto);
        loginLog.setUsrlId(null);
        usersLoginLogoutRepository.save(loginLog);
        return loginLog.getUsrlId();
    }

    @Override
    @Transactional
    public void updateUserLog(Long userId, Date usrLogoffTime, String usrEstimationTime) {
        UsersLoginLogout loginLog = usersLoginLogoutRepository.findByUsrlId(userId);
        loginLog.setUsrLogoffTime(usrLogoffTime);
        loginLog.setUsrEstimationTime(usrEstimationTime);
        usersLoginLogoutRepository.save(loginLog);
    }

    @Override
    public List<ApplicationsDto> loadApplications(String userName, String clntName) {
        return ApplicationsMapper.toApplicationsDtoList(applicationsRepository.getApplicationsForMemberAndClient(userName,clntName).orElse(Collections.emptyList()));
    }

    @Override
    public List<ApplicationScreensDto> loadApplicationScreens(String userName, String clntName, Long appId) {
        return ApplicationScreensMapper.toApplicationScreensDtoList(applicationScreensRepository.getApplicationScreensForMemberAndClient(userName,clntName,appId));
    }

    @Override
    public UsersInfoDto loadUsers(String userName) {
        return usersRepository.loadUserInfo(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<UsersDto> getAllUsersInfo(String userName, String clntName) {
        return UsersMapper.toUsersDtoList(usersRepository.getAllUsers(userName,clntName));
    }

    @Override
    public void updateUsers(UsersDto usersDto) {

    }

    @Override
    public String resetPassword(String userName) {
        String nwePass = RandomGenerator.generateRandomKey();
        Users user = usersRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(nwePass));
        user.setPswdExpiryDate(Date.from(LocalDate.now().plusDays(90).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        usersRepository.save(user);
        return nwePass;
    }

    @Override
    public String changePassword(String userName, String newPassword) {
            Users user = usersRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setPswdExpiryDate(java.sql.Date.valueOf(LocalDate.now().plusDays(90)));
            usersRepository.save(user);
            return "Password changed successfully";
    }

    @Override
    public void lockUser(String userName) {
        Users user = usersRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setActive(2);
        user.setUpdatedOn(new Date());
        usersRepository.save(user);
    }
}
