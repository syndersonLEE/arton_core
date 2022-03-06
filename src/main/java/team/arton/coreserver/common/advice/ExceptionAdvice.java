package team.arton.coreserver.common.advice;

import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.arton.coreserver.model.DefaultResponse;
import team.arton.coreserver.model.StatusType;

@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(JwtException.class)
    public DefaultResponse jwtErrorType(JwtException e) {
        logger.error(e.getMessage());
        return DefaultResponse.res(StatusType.UNAUTHORIZED);
    }
}
