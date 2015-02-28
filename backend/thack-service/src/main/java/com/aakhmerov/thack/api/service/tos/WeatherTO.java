package com.aakhmerov.thack.api.service.tos;

import org.joda.time.DateTime;

/**
 * Created by aakhmerov on 28.02.15.
 */
public class WeatherTO {

    private DateTime date;
    private String temperature;
    private String type;


    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
