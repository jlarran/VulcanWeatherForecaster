package ml.weather.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ml.weather.entity.WeatherType;
import ml.weather.util.WeatherForecaster;

public class WeatherReporter {

    private static Logger LOGGER = LoggerFactory.getLogger(WeatherReporter.class);
    
    private static Logger REPORT = LoggerFactory.getLogger("WeatherReport");

    public static void main(String[] args) {
	if (args.length != 2) {
	    throw new RuntimeException("Have to recieve the FROM which day TO which day do the report");
	}

	int from = Integer.parseInt(args[0]);
	int to = Integer.parseInt(args[1]);

	WeatherForecaster wf = new WeatherForecaster();
	WeatherSeason season = new WeatherSeason();

	for (int i = from; i <= to; i++) {
	    WeatherType weatherType = wf.forecast(i);
	    LOGGER.debug("Day {} Weather {}", i, weatherType);
	    
	    if (season.weatherType == null) {
		// Inicializacion
		season.init(weatherType, i, i, wf.triangleArea());
	    } else if (!season.weatherType.equals(weatherType)) {
		// Cambio el clima
		REPORT.info("{};{};{};{}", season.weatherType, season.fromDay, season.toDay,
		        (season.weatherType.equals(WeatherType.lluvia)) ? season.maxRainDay : "");
		season.init(weatherType, i, i, wf.triangleArea());
	    } else {
		// No cambio el clima
		season.setToDay(i, wf.triangleArea());
	    }
	}
    }

}
