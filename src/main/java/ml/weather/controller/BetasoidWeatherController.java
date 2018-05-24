package ml.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ml.weather.entity.Betasoid;
import ml.weather.entity.PlanetWeather;
import ml.weather.error.InvalidDayException;
import ml.weather.util.InitialCondition;
import ml.weather.util.WeatherForecaster;

@RestController
@RequestMapping("/clima/betasoide")
public class BetasoidWeatherController {
    
    private static Logger LOGGER = LoggerFactory.getLogger(BetasoidWeatherController.class);

    @RequestMapping(method = RequestMethod.GET)
    public PlanetWeather get(@RequestParam("Anio") int year, @RequestParam("diaDelAnio") int dayOfYear) {
	
	if (dayOfYear < 1 || dayOfYear > 120) {
	    throw new InvalidDayException("diaDelAnio=" + dayOfYear + " debe estar entre 1 y 120");
	}
	
	PlanetWeather response = new PlanetWeather();

	int yearsFromStart = year - InitialCondition.BETASOID.geInitialYear();
	int daysFromStart = dayOfYear - InitialCondition.BETASOID.geInitialDay();

	int day = new Betasoid(0).getDays(yearsFromStart, daysFromStart);

	LOGGER.debug("Betasoid forecast yearsFromsStart {} daysFromStart {} day {}", yearsFromStart, daysFromStart, day);

	response.setYear(year);
	response.setDayOfYear(dayOfYear);
	response.setWeather(new WeatherForecaster().forecast(day));

	return response;
    }
}
