package com.example.flink;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONWeatherMarineDeserializationSchema implements DeserializationSchema<WeatherMarine> {

    private static final long serialVersionUID = 1L;
    private transient ObjectMapper objectMapper;

    @Override
    public WeatherMarine deserialize(byte[] bytes) throws IOException {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        JsonNode rootNode = objectMapper.readTree(bytes);
        WeatherMarine weather = new WeatherMarine();

        weather.setLatitude(rootNode.path("latitude").asDouble());
        weather.setLongitude(rootNode.path("longitude").asDouble());
        // Extract hourly times
        List<String> hourlyTimes = extractHourlyTimes(rootNode);
        weather.setHourlyTime(hourlyTimes);

        // Extract wave heights
        List<Double> waveHeights = extractWaveHeights(rootNode);
        weather.setWaveHeight(waveHeights);

        return weather;
    }

    private List<String> extractHourlyTimes(JsonNode rootNode) {
        List<String> times = new ArrayList<>();
        JsonNode timeNode = rootNode.path("hourly").path("time");
        if (timeNode.isArray()) {
            for (JsonNode time : timeNode) {
                times.add(time.asText());
            }
        }
        return times;
    }

    private List<Double> extractWaveHeights(JsonNode rootNode) {
        List<Double> waveHeights = new ArrayList<>();
        JsonNode waveHeightNode = rootNode.path("hourly").path("wave_height");
        if (waveHeightNode.isArray()) {
            for (JsonNode waveHeight : waveHeightNode) {
                waveHeights.add(waveHeight.asDouble());
            }
        }
        return waveHeights;
    }

    @Override
    public boolean isEndOfStream(WeatherMarine weather) {
        return false;
    }

    @Override
    public TypeInformation<WeatherMarine> getProducedType() {
        return TypeInformation.of(WeatherMarine.class);
    }
}
