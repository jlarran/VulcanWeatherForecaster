package ml.weather.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ml.weather.entity.Weather;
import ml.weather.util.InitialCondition;
import ml.weather.util.WeatherForecaster;

@RestController
@RequestMapping("/clima")
public class WeatherController {

    @RequestMapping( method = RequestMethod.GET )
    public Weather get(@RequestParam("dia") int day) {
	Weather response = new Weather();

	response.setDay(day);
	response.setWeather(new WeatherForecaster().forecast(day + InitialCondition.getDaysFromInitial()));

	return response;
    }
}
