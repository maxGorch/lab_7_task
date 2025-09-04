package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.obj.House;

public class House_JacksonSeDe_tion
{
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String serializeToJson(House house) throws JsonProcessingException {
        return mapper.writeValueAsString(house);
    }
    public static House deserializeFromJson(String json) throws JsonProcessingException {
        return mapper.readValue(json,House.class);
    }

}
