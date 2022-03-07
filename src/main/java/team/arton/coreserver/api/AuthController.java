package team.arton.coreserver.api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team.arton.coreserver.common.auth.Auth;
import team.arton.coreserver.common.auth.AuthContext;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.model.*;
import team.arton.coreserver.service.AuthService;
import team.arton.coreserver.service.JwtService;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
public class AuthController {

    private AuthService authService;
    private JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @ApiOperation("로그인 요청")
    @PostMapping("/api/v1/login")
    public DefaultResponse userVerify(@Valid @RequestBody final UserReqDto userReqDto) {
        Optional<User> user = authService.findUser(userReqDto);
        if(user.isPresent()) {
            String token = jwtService.createToken(user.get().getId());
            return DefaultResponse.res(StatusType.OK, new UserResDto(user.get(), token, false));
        }

        return DefaultResponse.res(StatusType.NOCONTENT);
    }


    @ApiOperation("회원가입 요청")
    @PostMapping("/api/v1/signup")
    public DefaultResponse userSignup(@Valid @RequestBody final UserReqDto userReqDto) {
        User user = authService.saveUser(userReqDto);
        if(user == null) {
            return DefaultResponse.res(StatusType.CONFLICT);
        }

        String token = jwtService.createToken(user.getId());
        return DefaultResponse.res(StatusType.OK, new UserResDto(user, token, true));
    }

    @ApiIgnore
    @GetMapping("/test2")
    public ResponseEntity testMethod2() {
        String token = jwtService.createToken(1L);
        Map<String, Object> claims = jwtService.verifyToken(token);
        System.out.println(claims);
        return ResponseEntity.ok(claims);
    }

    @ApiIgnore
    @GetMapping("/authtest")
    @Auth
    public DefaultResponse testMethod3() {
        return DefaultResponse.res(StatusType.OK, AuthContext.getUserId());
    }
}
