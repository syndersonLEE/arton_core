package team.arton.coreserver.api;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.model.SocialType;
import team.arton.coreserver.model.UserReqDto;
import team.arton.coreserver.model.UserResDto;
import team.arton.coreserver.repository.AuthRepository;
import team.arton.coreserver.service.AuthService;
import utils.JwtParser;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class AuthController {

    private AuthService authService;
    private AuthRepository authRepository;

    @ApiOperation("로그인 요청")
    @PostMapping("/api/v1/login")
    public ResponseEntity userVerify(@Valid @RequestBody final UserReqDto userReqDto) {
        User user = authService.findUser(userReqDto);
        return ResponseEntity.ok(new UserResDto(user, false));
    }

    @ApiOperation("회원가입 요청")
    @PostMapping("/api/v1/signup")
    public ResponseEntity userSignup(@Valid @RequestBody final UserReqDto userReqDto) {
        User user = authService.saveUser(userReqDto);
        return ResponseEntity.status(201).body(new UserResDto(user, true));
    }

    @ApiIgnore
    @GetMapping("/test2")
    public ResponseEntity testMethod2() throws UnsupportedEncodingException {
        String token = JwtParser.createToken(1L);
        Map<String, Object> claims = JwtParser.verifyToken(token);
        System.out.println(claims);
        return ResponseEntity.ok(claims);
    }

    @ApiIgnore
    @GetMapping("/testing")
    public ResponseEntity testMethod3() {
        return ResponseEntity.ok("test3");
    }
}
