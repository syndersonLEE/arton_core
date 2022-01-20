package team.arton.coreserver.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.model.LoginDto;
import team.arton.coreserver.model.LoginResponseDto;
import team.arton.coreserver.model.SocialType;
import team.arton.coreserver.repository.AuthRepository;
import utils.JwtParser;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private AuthRepository authRepository;

    public LoginResponseDto login(final LoginDto loginDto) {
        String socialType = loginDto.typeName();
        String userId = loginDto.getUserId();

        Optional<User> user = authRepository.findUserByTypeAndServerid(socialType, userId);
        if(!user.isPresent()) {
            User mappingUser = User.builder()
                    .serverid(userId)
                    .type(socialType)
                    .nickname(loginDto.getNickname())
                    .build();
            log.info("test");
            return new LoginResponseDto(SocialType.GOOGLE, JwtParser.createToken(authRepository.save(mappingUser).getId()));
        }
        log.info("test");
        return new LoginResponseDto(SocialType.GOOGLE, JwtParser.createToken(user.get().getId()));

    }

    public String testMethod() {
        return authRepository.findById(1L).get().getNickname();
    }

}
