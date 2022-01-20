package team.arton.coreserver.api;

import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.arton.coreserver.model.LoginDto;
import team.arton.coreserver.model.LoginResponseDto;
import team.arton.coreserver.service.AuthService;
import utils.JwtParser;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
public class AuthController {

    private AuthService authService;

    @ApiOperation("로그인 요청")
    @PostMapping("/login")
    public ResponseEntity postLogin(@Valid @RequestBody final LoginDto loginDto) {
        LoginResponseDto loginResponseDto = authService.login(loginDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    @GetMapping("/test2")
    public ResponseEntity testMethod2() throws UnsupportedEncodingException {
        String token = JwtParser.createToken(1L);
        Map<String, Object> claims = JwtParser.verifyToken(token);
        System.out.println(claims);
        return ResponseEntity.ok(claims);
    }

    @GetMapping("/testing")
    public ResponseEntity testMethod3() {
        return ResponseEntity.ok("test3");
    }
}
