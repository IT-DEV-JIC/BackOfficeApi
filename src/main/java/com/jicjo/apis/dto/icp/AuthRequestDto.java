package com.jicjo.apis.dto.icp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class AuthRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
