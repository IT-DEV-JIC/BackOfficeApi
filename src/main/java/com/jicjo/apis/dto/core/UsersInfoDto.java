package com.jicjo.apis.dto.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersInfoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String clntName;
    private String fullArFullName;
    private String fullEnName;
    private Integer type;
    private String preferredLang;
}
