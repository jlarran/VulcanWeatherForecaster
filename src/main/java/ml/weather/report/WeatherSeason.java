package ml.weather.report;

import ml.weather.entity.WeatherType;

public class WeatherSeason {

    public WeatherType weatherType = null;

    public Integer fromDay = null;

    public Integer toDay = null;

    public Integer maxRainDay = null;

    public int maxRainArea = 0;

    public void init(WeatherType weatherType, int fromDay, int toDay, int area) {
	this.weatherType = weatherType;
	this.fromDay = fromDay;
	this.toDay = toDay;

	if (weatherType.equals(WeatherType.lluvia)) {
	    maxRainDay = fromDay;
	    maxRainArea = area;
	}
    }

    public void setToDay(int i, int area) {
	if (weatherType.equals(WeatherType.lluvia)) {
	    if (area > maxRainArea) {
		this.maxRainArea = area;
		this.maxRainDay = i;
	    }
	}

	this.toDay = i;
    }

}
