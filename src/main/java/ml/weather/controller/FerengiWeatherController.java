package ml.weather.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ml.weather.entity.Ferengi;
import ml.weather.entity.PlanetWeather;
import ml.weather.error.InvalidDayException;
import ml.weather.util.InitialCondition;
import ml.weather.util.WeatherForecaster;

@RestController
@RequestMapping("/clima/ferengi")
public class FerengiWeatherController {

    @RequestMapping(method = RequestMethod.GET)
    public PlanetWeather get(@RequestParam("Anio") int year, @RequestParam("diaDelAnio") int dayOfYear) {
	
	if (dayOfYear < 1 || dayOfYear > 360) {
	    throw new InvalidDayException("diaDelAnio=" + dayOfYear + " debe estar entre 1 y 360");
	}

	
	PlanetWeather response = new PlanetWeather();

	int yearsFromStart = year - InitialCondition.FERENGI.geInitialYear();
	int daysFromStart = dayOfYear - InitialCondition.FERENGI.geInitialDay();

	int day = new Ferengi(0).getDays(yearsFromStart, daysFromStart);

	response.setYear(year);
	response.setDayOfYear(dayOfYear);
	response.setWeather(new WeatherForecaster().forecast(day));

	return response;
    }
}
