package ml.weather.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDayException extends RuntimeException {

    
    public InvalidDayException(String message) {
	super(message);
    }
}
