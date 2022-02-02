package team.arton.coreserver.exception;

public class BussinessException extends RuntimeException {
    public BussinessException(String errorType) {
        super(errorType);
    }
}
