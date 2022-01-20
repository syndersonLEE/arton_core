package team.arton.coreserver.exception;

public class SocialLoginTypeException extends InvalidValueException {
    public SocialLoginTypeException() {
        super("400 BadRequest");
    }

}
