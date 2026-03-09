package com.jicjo.apis.controller.core;

import com.jicjo.apis.config.AESUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//@ControllerAdvice
public class EncryptResponseAdvice implements ResponseBodyAdvice<Object> {

    private final AESUtil aesUtil;

    public EncryptResponseAdvice(AESUtil aesUtil) {
        this.aesUtil = aesUtil;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true; // Encrypt all responses
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        try {
            String json = body instanceof String ? (String) body :
                    new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(body);
            return aesUtil.encrypt(json);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }
}
