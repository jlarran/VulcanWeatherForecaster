package ml.weather.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum InitialCondition {

    VULCAN("vulcan", "0"),

    FERENGI("ferengi", "0"),

    BETASOID("betasoid", "0");

    private static final String PROPERTY_FILENAME = "/initial_conditions.properties";

    private static final String INITIAL_ANGLE_SUFFIX = ".initial.angle";

    private static final String INITIAL_DAY_SUFFIX = ".initial.day";

    private static final String INITIAL_YEAR_SUFFIX = ".initial.year";

    private static final String INITIAL_DATE = "initial.date";

    private static final Logger LOGGER = LoggerFactory.getLogger(InitialCondition.class);

    private static Properties prop = new Properties();

    static {
	try {
	    prop.load(InitialCondition.class.getResourceAsStream(PROPERTY_FILENAME));
	} catch (Exception e) {
	    LOGGER.error("Error loading properties", e);
	}
    }

    private String propertyName;

    private String defaultValue;

    private InitialCondition(String propertyName, String defaultValue) {
	this.propertyName = propertyName;
	this.defaultValue = defaultValue;
    }

    public int geInitialAngle() {
	LOGGER.debug("{} inital angle property {} value {}", propertyName, propertyName + INITIAL_ANGLE_SUFFIX,
	        prop.getProperty(propertyName + INITIAL_ANGLE_SUFFIX));
	return Integer.parseInt(prop.getProperty(propertyName + INITIAL_ANGLE_SUFFIX, defaultValue));
    }

    public int geInitialDay() {
	LOGGER.debug("{} inital day property {} value {}", propertyName, propertyName + INITIAL_DAY_SUFFIX,
	        prop.getProperty(propertyName + INITIAL_DAY_SUFFIX));
	return Integer.parseInt(prop.getProperty(propertyName + INITIAL_DAY_SUFFIX, defaultValue));
    }

    public int geInitialYear() {
	LOGGER.debug("{} inital year property {} value {}", propertyName, propertyName + INITIAL_YEAR_SUFFIX,
	        prop.getProperty(propertyName + INITIAL_YEAR_SUFFIX));
	return Integer.parseInt(prop.getProperty(propertyName + INITIAL_YEAR_SUFFIX, defaultValue));
    }

    public static int getDaysFromInitial() {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	long diff = 0;

	try {
	    Date initialDate = sdf.parse(getInitialDate());

	    diff = new Date().getTime() - initialDate.getTime();
	} catch (ParseException e) {
	    LOGGER.error("Error parsing initial date property", e);
	}

	return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static String getInitialDate() {
	return prop.getProperty(INITIAL_DATE);
    }

    public String toString() {
	StringBuilder toString = new StringBuilder();

	toString.append("<b>").append(name()).append(" condiciones iniciales:</b><br>");
	toString.append("- Angulo inicial: ").append(this.geInitialAngle());
	toString.append("<br>- Dia inicial: ").append(this.geInitialDay());
	toString.append("<br>- Año inicial: ").append(this.geInitialYear());

	return toString.toString();
    }

}

