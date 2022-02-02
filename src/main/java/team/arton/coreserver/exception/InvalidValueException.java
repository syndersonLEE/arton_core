package team.arton.coreserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidValueException extends BussinessException {

    public InvalidValueException(String errorType) {
        super(errorType);
    }
}
