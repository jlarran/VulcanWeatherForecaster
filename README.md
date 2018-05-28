# VulcanWeatherForecaster
A Weather Forecaster Platform for the Vulcan planetary system

El proyecto contiene una SpringBoot REST-API a la que se puede consultar el clima por dia del sistema planetario o por año y dia de un planeta en particular.
Para desplegarlo en Google AppEngine revisar los comentarios del pom.xml para quitar las dependencias del log4j.

Adicionalmente contiene un ejecutable en ml.weather.report.WeatherReporter que escribe en el logger "Report" un csv con las sesiones climaticas con el siguiente formato clima;dia desde;dia hasta;dia de mayor lluvia.
Ej:
lluvia;1;8;5
sequia;9;9;
