package team.arton.coreserver.common.auth;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.model.DefaultResponse;
import team.arton.coreserver.model.StatusType;
import team.arton.coreserver.repository.AuthRepository;
import team.arton.coreserver.service.JwtService;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String AUTHORIZATION = "Authorization";

    private static final DefaultResponse FAIL_AUTHORIZATION = DefaultResponse.res(StatusType.UNAUTHORIZED);

    private final HttpServletRequest httpServletRequest;

    private final AuthRepository authRepository;

    private final JwtService jwtService;

    public AuthAspect(HttpServletRequest httpServletRequest, AuthRepository authRepository, JwtService jwtService) {
        this.httpServletRequest = httpServletRequest;
        this.authRepository = authRepository;
        this.jwtService = jwtService;
    }

    @Around("@annotation(team.arton.coreserver.common.auth.Auth)")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable {
        String jwt = httpServletRequest.getHeader(AUTHORIZATION);
        logger.info("Request token : {}", jwt);

        final Long userId = Long.valueOf((Integer) jwtService.verifyToken(jwt).get("id"));

        if(userId == null) {
            return DefaultResponse.res(StatusType.UNAUTHORIZED);
        }

        final User user =  authRepository.findById(userId).orElse(null);
        logger.info("nickname : {} - authorizaiton complete", user.getNickname());
        AuthContext.userContext.set(user);
        return pjp.proceed();

    }
}
