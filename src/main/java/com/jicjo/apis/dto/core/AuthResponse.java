package com.jicjo.apis.dto.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String token;
}
