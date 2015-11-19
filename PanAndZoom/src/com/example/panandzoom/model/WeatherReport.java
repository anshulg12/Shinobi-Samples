
package com.example.panandzoom.model;

import java.util.Date;

public class WeatherReport {

    private final Date date;
    private final double minTemperature;
    private final double maxTemperature;

    public WeatherReport(Date date, double minTemperature, double maxTemperature) {
        this.date = date;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public Date getDate() {
        return date;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

}
