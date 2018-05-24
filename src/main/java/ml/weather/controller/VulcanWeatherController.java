package ml.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ml.weather.entity.PlanetWeather;
import ml.weather.entity.Vulcan;
import ml.weather.error.InvalidDayException;
import ml.weather.util.InitialCondition;
import ml.weather.util.WeatherForecaster;

@RestController
@RequestMapping("/clima/vulcano")
public class VulcanWeatherController {

    private static Logger LOGGER = LoggerFactory.getLogger(VulcanWeatherController.class);

    @RequestMapping(method = RequestMethod.GET)
    public PlanetWeather get(@RequestParam("Anio") int year, @RequestParam("diaDelAnio") int dayOfYear) {

	if (dayOfYear < 1 || dayOfYear > 72) {
	    throw new InvalidDayException("diaDelAnio=" + dayOfYear + " debe estar entre 1 y 72");
	}

	PlanetWeather response = new PlanetWeather();

	int yearsFromStart = year - InitialCondition.VULCAN.geInitialYear();
	int daysFromStart = dayOfYear - InitialCondition.VULCAN.geInitialDay();

	int day = new Vulcan(0).getDays(yearsFromStart, daysFromStart);

	LOGGER.debug("Vulcan forecast yearsFromsStart {} daysFromStart {} day {}", yearsFromStart, daysFromStart, day);
	
	response.setYear(year);
	response.setDayOfYear(dayOfYear);
	response.setWeather(new WeatherForecaster().forecast(day));

	return response;
    }
}
