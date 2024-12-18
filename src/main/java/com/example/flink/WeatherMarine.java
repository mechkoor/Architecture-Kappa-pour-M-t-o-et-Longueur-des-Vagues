package com.example.flink;

import java.util.List;

public class WeatherMarine {
    private double latitude;
    private double longitude;
    private List<String> hourlyTime;
    private List<Double> waveHeight;


    // Constructors, getters, setters
    public WeatherMarine() {}

    public WeatherMarine(double latitude, double longitude, List<String> hourlyTime, List<Double> waveHeight) {
        this.hourlyTime = hourlyTime;
        this.waveHeight = waveHeight;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<String> getHourlyTime() {
        return hourlyTime;
    }

    public void setHourlyTime(List<String> hourlyTime) {
        this.hourlyTime = hourlyTime;
    }

    public List<Double> getWaveHeight() {
        return waveHeight;
    }

    public void setWaveHeight(List<Double> waveHeight) {
        this.waveHeight = waveHeight;
    }
}
