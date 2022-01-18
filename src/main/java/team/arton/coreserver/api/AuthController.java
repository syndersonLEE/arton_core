package team.arton.coreserver.api;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.arton.coreserver.service.AuthService;
import utils.JwtParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@Slf4j
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public ResponseEntity postLogin() {
//        String token = JwtParser.createToken(1L);
//        Map<String, Object> claims = JwtParser.verifyToken(token);
//        System.out.println(claims);


        return ResponseEntity.ok(authService.testMethod());
    }

    @GetMapping("/test2")
    public ResponseEntity testMethod2() {
        return ResponseEntity.ok("test");
    }

    @GetMapping("/testing")
    public ResponseEntity testMethod3() {
        return ResponseEntity.ok("test3");
    }
}
