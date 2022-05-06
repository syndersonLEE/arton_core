package team.arton.coreserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.exception.InvalidValueException;
import team.arton.coreserver.model.reqdto.UserReqDto;
import team.arton.coreserver.repository.AuthRepository;


import java.util.Optional;

@Slf4j
@Service
public class AuthService {
    private AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Optional<User> findUser(final UserReqDto userReqDto){
        try {
            Optional<User> user = authRepository.findUserByTypeAndServerid(userReqDto.typeName(), userReqDto.getUserId());
            return user;
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new InvalidValueException("not valid");
        }
    }

    @Transactional
    public User saveUser(final UserReqDto userReqDto) {
        try {
            Optional<User> nicknameChecker = authRepository.findUserByNickname(userReqDto.getNickname());
            Optional<User> userIdChecker = authRepository.findUserByServerid(userReqDto.getUserId());
            if (nicknameChecker.isPresent() || userIdChecker.isPresent()) {
                return null;
            }
            User newUser = User.builder()
                    .type(userReqDto.typeName().toLowerCase())
                    .serverid(userReqDto.getUserId())
                    .nickname(userReqDto.getNickname())
                    .build();

            return authRepository.save(newUser);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new InvalidValueException("not valid");
        }
    }

    public String testMethod() {
        return authRepository.findById(1L).get().getNickname();
    }

}
