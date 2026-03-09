package com.jicjo.apis.controller.core;

import com.jicjo.apis.dto.core.*;
import com.jicjo.apis.mapper.core.UsersMapper;
import com.jicjo.apis.model.core.Users;
import com.jicjo.apis.repository.core.UsersRepository;
import com.jicjo.apis.service.core.UsersService;
import com.jicjo.apis.service.core.impl.OtpService;
import com.jicjo.apis.utility.JwtUtil;
import com.jicjo.apis.utility.TokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/core")
public class UsersController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private OtpService otpService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UsersDto usersDto) {
        usersService.registerUser(usersDto);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        authRequest.setUsername(authRequest.getUsername().toLowerCase());
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "ERRC0001"));//Invalid username/password
            //throw new Exception("ERRC0001");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        UsersDto usersDto = UsersMapper.toUsersDto(usersRepository.findByUsername(authRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found")));

        if (usersDto.getActive() != null && usersDto.getActive() == 1){

        } else if (usersDto.getActive() != null && usersDto.getActive() == 2) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "ERRC0002"));//The user is locked
        }else if (usersDto.getActive() != null && usersDto.getActive() == 3) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "ERRC0003"));//The user was inactive
        }else if (usersDto.getActive() != null && usersDto.getActive() == 4) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "ERRC0004"));//The user was expired
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "ERRC0005"));//Invalid user status
        }

        if (usersDto.getPswdExpiryDate() != null && new Date().after(usersDto.getPswdExpiryDate())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "ERRC0006"));//Password expired. Please reset your password.
        }

        final String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    /*@PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("errorCode", "ERRC0001")); // Invalid username/password
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        UsersDto usersDto = UsersMapper.toUsersDto(
                usersRepository.findByUsername(authRequest.getUsername())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"))
        );

        if (usersDto.getActive() != null && usersDto.getActive() != 1) {
            return switch (usersDto.getActive()) {
                case 2 -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "ERRC0002")); // Locked
                case 3 -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "ERRC0003")); // Inactive
                case 4 -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "ERRC0004")); // Expired
                default -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "ERRC0005")); // Invalid status
            };
        }

        if (usersDto.getPswdExpiryDate() != null && new Date().after(usersDto.getPswdExpiryDate())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "ERRC0006")); // Password expired
        }

        // ✅ Send OTP via SMS instead of returning token
        otpService.generateAndSendOtp(authRequest.getUsername(), usersDto.getPhone());
        return ResponseEntity.ok(Map.of("status", "OTP_SENT"));
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request) {
        boolean valid = otpService.verifyOtp(request.getUsername(), request.getOtp());
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("errorCode", "INVALID_OTP"));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtUtil.generateToken(userDetails.getUsername());

        otpService.clearOtp(request.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }*/

    @PostMapping("/forgotPassword/sendOtp")
    public ResponseEntity<?> sendResetOtp(@RequestBody Map<String, String> req) {
        String username = req.get("username");
        Users users = usersRepository.findByUsername(username.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (users.getPhone() == null || users.getPhone().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("errorCode", "ERR_NO_PHONE"));
        }

        otpService.generateAndSendOtp(username, users.getPhone());

        return ResponseEntity.ok(Map.of("status", "OTP_SENT"));
    }

    @PostMapping("/forgotPassword/reset")
    public ResponseEntity<?> resetPasswordWithOtp(@RequestBody ResetPasswordOtpRequestDto request) {
        request.setUsername(request.getUsername().toLowerCase());
        boolean isValid = otpService.verifyOtp(request.getUsername(), request.getOtp());
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorCode", "INVALID_OTP"));
        }

        String status = usersService.changePassword(request.getUsername(),request.getNewPassword());
        otpService.clearOtp(request.getUsername());

        return ResponseEntity.ok(Map.of("status", status));
    }

    @GetMapping("/validateToken")
    public ResponseEntity<?> validateToken(HttpServletRequest request, @RequestParam String username) {
        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username.toLowerCase());
                boolean isValid = jwtUtil.validateToken(token, userDetails);
                return ResponseEntity.ok(Map.of("valid", isValid,"error",""));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("valid", false, "error", "Missing or invalid Authorization header"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("valid", false, "error", e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
        }

        return ResponseEntity.ok().body("{\"message\":\"User logged out\"}");
    }

    @PostMapping("/loginLog")
    public ResponseEntity<?> loginLog(@RequestBody UsersLoginLogoutDto usersLoginLogoutDto) {
        usersLoginLogoutDto.setUsrName(usersLoginLogoutDto.getUsrName().toLowerCase());
        return ResponseEntity.ok(usersService.addUserLog(usersLoginLogoutDto));
    }

    @PutMapping("/logoutLog")
    public ResponseEntity<?> logoutLog(@RequestBody UsersLoginLogoutDto usersLoginLogoutDto) {
        usersService.updateUserLog(usersLoginLogoutDto.getUsrlId(),usersLoginLogoutDto.getUsrLogoffTime(),
                                   usersLoginLogoutDto.getUsrEstimationTime());
        return ResponseEntity.ok().body("{\"message\":\"User logged out\"}");
    }

    @GetMapping("/getApplications")
    public ResponseEntity<?> getApplications(@RequestParam String userName, @RequestParam String clntName) {
        return ResponseEntity.ok().body(usersService.loadApplications(userName.toLowerCase(), clntName));
    }

    @GetMapping("/getApplicationScreens")
    public ResponseEntity<?> getApplicationScreens(@RequestParam String userName, @RequestParam String clntName, @RequestParam Long appId) {
        return ResponseEntity.ok().body(usersService.loadApplicationScreens(userName.toLowerCase(), clntName,appId));
    }

    @GetMapping("/loadUsersInfo")
    public ResponseEntity<?> loadUsersInfo(@RequestParam String userName) {
        return ResponseEntity.ok().body(usersService.loadUsers(userName.toLowerCase()));
    }

    @GetMapping("/getAllUsersInfo")
    public ResponseEntity<?> getAllUsersInfo(@RequestParam String userName, @RequestParam String clntName) {
        return ResponseEntity.ok().body(usersService.getAllUsersInfo(userName.toLowerCase(), clntName));
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam String userName) {
        return ResponseEntity.ok(usersService.resetPassword(userName.toLowerCase()));
    }

    @PutMapping("/updateUsers")
    public ResponseEntity<?> updateUsers(@RequestBody UsersDto usersDto) {
        usersDto.setUserName(usersDto.getUserName().toLowerCase());
        usersService.updateUsers(usersDto);
        return ResponseEntity.ok("User Updated");
    }
}