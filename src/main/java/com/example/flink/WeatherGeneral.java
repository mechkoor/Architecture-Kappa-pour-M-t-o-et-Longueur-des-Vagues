package com.example.flink;


public class WeatherGeneral {
    private String cityName;
    private double temperature;
    private double windSpeed;
    private int windDeg;
    private int timezone;
    private double latitude; 
    private double longitude; 

    // Constructeur par défaut
    public WeatherGeneral() {
    }

    // Constructeur avec tous les champs
    public WeatherGeneral(String cityName, double temperature, double windSpeed, int windDeg, int timezone, double latitude, double longitude) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.timezone = timezone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters et setters pour chaque champ
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public double getLatitude() { // Getter pour latitude
        return latitude;
    }

    public void setLatitude(double latitude) { // Setter pour latitude
        this.latitude = latitude;
    }

    public double getLongitude() { // Getter pour longitude
        return longitude;
    }

    public void setLongitude(double longitude) { // Setter pour longitude
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "cityName='" + cityName + '\'' +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", windDeg=" + windDeg +
                ", timezone=" + timezone +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
