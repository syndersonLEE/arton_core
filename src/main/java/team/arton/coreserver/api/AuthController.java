package team.arton.coreserver.api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import team.arton.coreserver.common.auth.Auth;
import team.arton.coreserver.common.auth.AuthContext;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.model.*;
import team.arton.coreserver.service.AuthService;
import team.arton.coreserver.service.JwtService;
import team.arton.coreserver.service.S3FileUploadService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
public class AuthController {

    private AuthService authService;
    private JwtService jwtService;
    private S3FileUploadService s3FileUploadService;

    public AuthController(AuthService authService, JwtService jwtService, S3FileUploadService s3FileUploadService) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.s3FileUploadService = s3FileUploadService;
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
    @GetMapping("/authtest")
    @Auth
    public DefaultResponse testMethod3() {
        return DefaultResponse.res(StatusType.OK, AuthContext.getUserId());
    }

    @ApiIgnore
    @PostMapping("/s3test")
    public DefaultResponse testMethod(@RequestPart(value = "profile", required = false) final MultipartFile file) {
        String url = "";
        try {
            url = s3FileUploadService.upload(file);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return DefaultResponse.res(StatusType.OK, url);
    }
}
