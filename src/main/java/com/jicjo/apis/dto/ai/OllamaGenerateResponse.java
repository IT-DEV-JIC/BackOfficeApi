package com.jicjo.apis.dto.ai;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class OllamaGenerateResponse  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String model;
    private String response;
    private Boolean done;
    private String done_reason;
    private Long total_duration;
    private Long load_duration;
    private Integer prompt_eval_count;
    private Long prompt_eval_duration;
    private Integer eval_count;
    private Long eval_duration;
}
