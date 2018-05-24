package ml.weather.entity;

public class Weather {

    private int day;
    
    private WeatherType weather;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public WeatherType getWeather() {
        return weather;
    }

    public void setWeather(WeatherType weather) {
        this.weather = weather;
    }
    
    
}
