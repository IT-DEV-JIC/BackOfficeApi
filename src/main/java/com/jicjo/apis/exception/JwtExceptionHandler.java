package com.jicjo.apis.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serial;
import java.io.Serializable;

@RestControllerAdvice("com.jicjo.apis.handler")
public class JwtExceptionHandler implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
