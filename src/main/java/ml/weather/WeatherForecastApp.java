package ml.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import ml.weather.util.InitialCondition;

@SpringBootApplication
@RestController
public class WeatherForecastApp {
    public static void main(String[] args) {
	SpringApplication.run(WeatherForecastApp.class, args);
    }

    @GetMapping("/")
    public String help() {
	StringBuilder help = new StringBuilder();

        help.append("<h1>Bienvenido al Servicio Meteorológico Vulcaniano</h1>");
	help.append("Para usar el servicio puede acceder a traves de las siguientes url:<br>");
	help.append("<br><b>/clima?dia=x");
	help.append("<br>Servicio General:</b> dia tiene que ser entero (positivo o negativo) y muestra el clima<br>");
	help.append("de los dias a partir de hoy. Ej: /clima?dia=1 mostrará el clima del dia de mañana.<br>");
	help.append("<br><b>/clima/betasoide?Anio=y&diaDelAnio=x");
	help.append("<br>Servicio para Betasoide:</b> Anio es el año de Betasoide y diaDelAnio es el dia (entre 1 y 120) del que se quiere saber el clima.<br>");
	help.append("<br><b>/clima/ferengi?Anio=y&diaDelAnio=x");
	help.append("<br>Servicio para Ferengi:</b> Anio es el año de Ferengi y diaDelAnio es el dia (entre 1 y 360) del que se quiere saber el clima.<br>");
	help.append("<br><b>/clima/vulcano?Anio=y&diaDelAnio=x<br>");
	help.append("Servicio para Vulcano:</b> Anio es el año de Vulcano y diaDelAnio es el dia (entre 1 y 72) del que se quiere saber el clima.<br>");

	help.append("<h2>Condiciones Iniciales</h2>");
	help.append("<b>Dia de inicio del sistema: </b>").append(InitialCondition.getInitialDate());
	help.append("<br>").append(InitialCondition.BETASOID.toString());
	help.append("<br>").append(InitialCondition.FERENGI.toString());
	help.append("<br>").append(InitialCondition.VULCAN.toString());
	return help.toString();
    }
}
