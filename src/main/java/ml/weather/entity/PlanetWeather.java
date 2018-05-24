package ml.weather.entity;

public class PlanetWeather {
    private int year;

    private int dayOfYear;

    private WeatherType weather;

    public int getYear() {
	return year;
    }

    public void setYear(int year) {
	this.year = year;
    }

    public int getDayOfYear() {
	return dayOfYear;
    }

    public void setDayOfYear(int dayOfYear) {
	this.dayOfYear = dayOfYear;
    }

    public WeatherType getWeather() {
	return weather;
    }

    public void setWeather(WeatherType weather) {
	this.weather = weather;
    }
}
