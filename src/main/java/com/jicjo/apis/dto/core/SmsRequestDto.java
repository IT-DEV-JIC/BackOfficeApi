package com.jicjo.apis.dto.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jicjo.apis.utility.StringOrArrayDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmsRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("service_type")
    @JsonDeserialize(using = StringOrArrayDeserializer.class)
    private String service_type;
    @JsonProperty("recipient_numbers_type")
    @JsonDeserialize(using = StringOrArrayDeserializer.class)
    private String recipient_numbers_type;
    @JsonProperty("phone_numbers")
    @JsonDeserialize(using = StringOrArrayDeserializer.class)
    private List<String> phone_numbers = new ArrayList<String>();
    @JsonProperty("content")
    @JsonDeserialize(using = StringOrArrayDeserializer.class)
    private String content;
    @JsonProperty("sender_id")
    @JsonDeserialize(using = StringOrArrayDeserializer.class)
    private String sender_id;
}
