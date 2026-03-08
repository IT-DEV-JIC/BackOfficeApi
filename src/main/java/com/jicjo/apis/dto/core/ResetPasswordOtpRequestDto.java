package com.jicjo.apis.dto.core;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ResetPasswordOtpRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String username;
    private String otp;
    private String newPassword;
}
