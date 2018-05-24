package ml.weather.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ml.weather.entity.Betasoid;
import ml.weather.entity.Ferengi;
import ml.weather.entity.Planet;
import ml.weather.entity.Sun;
import ml.weather.entity.Vulcan;
import ml.weather.entity.WeatherType;

public class WeatherForecaster {

    private static final double ERROR_THRESHOLD = 30000;

    private static Logger LOGGER = LoggerFactory.getLogger(WeatherForecaster.class);

    private static final int CYCLE_DURATION = 360;

    private static final Sun sun = new Sun();

    private Planet vulcan;

    private Planet ferengi;

    private Planet betasoid;

    public WeatherType forecast(int days) {
	vulcan = new Vulcan(InitialCondition.VULCAN.geInitialAngle());
	ferengi = new Ferengi(InitialCondition.FERENGI.geInitialAngle());
	betasoid = new Betasoid(InitialCondition.BETASOID.geInitialAngle());

	// Get the day inside a cycle
	int daysInCycle = (int) (days % CYCLE_DURATION);

	// Move the planets to that position
	vulcan.rotate(daysInCycle);
	ferengi.rotate(daysInCycle);
	betasoid.rotate(daysInCycle);

	LOGGER.debug("Vulcan {}", vulcan);
	LOGGER.debug("Ferengi {}", ferengi);
	LOGGER.debug("Betasoid {}", betasoid);

	int orientation = triangleArea();

	LOGGER.debug("Orientation {}", orientation);
	// Evaluate if it's a triangle or a straight line
	if (Math.abs(orientation) < ERROR_THRESHOLD) {
	    // Straight line
	    int sunOrientation = triangleArea(vulcan, ferengi, sun);

	    // Evaluate if the sun is aligned
	    if (Math.abs(sunOrientation) < ERROR_THRESHOLD) {
		return WeatherType.sequia;
	    } else {
		return WeatherType.optimo;
	    }
	} else {
	    // Triangle
	    if (insideArea(vulcan, ferengi, betasoid, sun)) {
		return WeatherType.lluvia;
	    }
	}

	return WeatherType.normal;
    }

    public int triangleArea() {
	return triangleArea(vulcan, ferengi, betasoid);
    }

    private int triangleArea(Planet a, Planet b, Planet c) {
	return (a.x - c.x) * (b.y - c.y) - (a.y - c.y) * (b.x - c.x);
    }

    private boolean insideArea(Planet a, Planet b, Planet c, Planet p) {
	if (triangleArea(a, b, c) >= 0)
	    return triangleArea(a, b, p) >= 0 && triangleArea(b, c, p) >= 0 && triangleArea(c, a, p) >= 0;
	else
	    return triangleArea(a, b, p) <= 0 && triangleArea(b, c, p) <= 0 && triangleArea(c, a, p) <= 0;
    }
}
