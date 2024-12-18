package com.example.flink;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Convertir Weather en JSON
    public static String convertWeatherToJson(CombinedWeather weather) {
        try {
            return objectMapper.writeValueAsString(weather);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Convertir JSON en Weather
    public static CombinedWeather convertJsonToWeather(String json) {
        try {
            return objectMapper.readValue(json, CombinedWeather.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
