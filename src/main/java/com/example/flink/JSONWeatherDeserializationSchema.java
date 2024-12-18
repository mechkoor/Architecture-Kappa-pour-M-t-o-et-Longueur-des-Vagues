package com.example.flink;

import java.io.IOException;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONWeatherDeserializationSchema implements DeserializationSchema<WeatherGeneral> {

    private static final long serialVersionUID = 1L;
    private transient ObjectMapper objectMapper;

    @Override
    public WeatherGeneral deserialize(byte[] bytes) throws IOException {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        JsonNode rootNode = objectMapper.readTree(bytes);
        WeatherGeneral weather = new WeatherGeneral();

        // Extraire les informations nécessaires
        weather.setCityName(rootNode.path("name").asText());
        weather.setTemperature(rootNode.path("main").path("temp").asDouble());
        weather.setWindSpeed(rootNode.path("wind").path("speed").asDouble());
        weather.setWindDeg(rootNode.path("wind").path("deg").asInt());
        weather.setTimezone(rootNode.path("timezone").asInt());
        
        // Extraire la latitude et la longitude
        weather.setLatitude(rootNode.path("coord").path("lat").asDouble());
        weather.setLongitude(rootNode.path("coord").path("lon").asDouble());

        return weather;
    }

    @Override
    public boolean isEndOfStream(WeatherGeneral weather) {
        return false;
    }

    @Override
    public TypeInformation<WeatherGeneral> getProducedType() {
        return TypeInformation.of(WeatherGeneral.class);
    }
}
