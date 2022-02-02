package team.arton.coreserver.exception;

public class InvalidValueException extends BussinessException {

    public InvalidValueException(String errorType) {
        super(errorType);
    }
}
