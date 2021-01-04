package com.WeatherReporting.API.resources;

public enum APIResources {

    GetWeatherAPI("/data/2.5/weather");


    private String resources;

    APIResources(String resources) {
        this.resources = resources;
    }

    public String getResources() {
        return resources;
    }

}
