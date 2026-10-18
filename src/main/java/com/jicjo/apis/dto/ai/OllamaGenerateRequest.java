package com.jicjo.apis.dto.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OllamaGenerateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String model;
    private String system;
    private String prompt;
    private Boolean stream;
    private Boolean think;
    private String format;
    private String keep_alive;
    private Map<String, Object> options;
}