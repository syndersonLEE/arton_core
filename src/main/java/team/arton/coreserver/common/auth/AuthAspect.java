package team.arton.coreserver.common.auth;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import team.arton.coreserver.model.DefaultResponse;
import team.arton.coreserver.model.StatusType;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String AUTHORIZATION = "Authorization";

    private static final DefaultResponse FAIL_AUTHORIZATION = DefaultResponse.res(StatusType.UNAUTHORIZED);


}
