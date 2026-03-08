package com.jicjo.apis.utility;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringOrArrayDeserializer extends JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        // If the value is an array, return it as a list of strings
        if (node.isArray()) {
            List<String> result = new ArrayList<>();
            for (JsonNode item : node) {
                result.add(item.asText());
            }
            return result;
        } else {
            // Otherwise, return the value as a single string
            return node.asText();
        }
    }
}
