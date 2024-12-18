package com.example.flink;

public class CombinedWeather {
    private WeatherGeneral weatherGeneral;
    private WeatherMarine weatherMarine;

    // Constructeurs, getters, setters
    public CombinedWeather() {}

    public CombinedWeather(WeatherGeneral weatherGeneral, WeatherMarine weatherMarine) {
        this.weatherGeneral = weatherGeneral;
        this.weatherMarine = weatherMarine;
    }

    public WeatherGeneral getWeatherGeneral() {
        return weatherGeneral;
    }

    public void setWeatherGeneral(WeatherGeneral weatherGeneral) {
        this.weatherGeneral = weatherGeneral;
    }

    public WeatherMarine getWeatherMarine() {
        return weatherMarine;
    }

    public void setWeatherMarine(WeatherMarine weatherMarine) {
        this.weatherMarine = weatherMarine;
    }
}
