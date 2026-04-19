package com.jicjo.apis.dto.general;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccidentHeatmapDto  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long ID;
    private String LONGITUDE;
    private String LATITUDE;

}
