package team.arton.coreserver.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.exception.DuplicateException;
import team.arton.coreserver.exception.NotFoundException;
import team.arton.coreserver.model.UserReqDto;
import team.arton.coreserver.model.UserResDto;
import team.arton.coreserver.model.SocialType;
import team.arton.coreserver.repository.AuthRepository;
import utils.JwtParser;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private AuthRepository authRepository;

    public User findUser(final UserReqDto userReqDto) throws NotFoundException {
        Optional<User> user = authRepository.findUserByTypeAndServerid(userReqDto.typeName(), userReqDto.getUserId());
        return user.orElseThrow(() -> new NotFoundException("로그인 실패"));
    }

    public User saveUser(final UserReqDto userReqDto) throws DuplicateException {
        Optional<User> user = authRepository.findUserByNickname(userReqDto.getNickname());
        if(!user.isEmpty()) {
            throw new DuplicateException("닉네임 중첩");
        }
        User newUser = User.builder()
                .type(userReqDto.typeName().toLowerCase())
                .serverid(userReqDto.getUserId())
                .nickname(userReqDto.getNickname())
                .build();

        return authRepository.save(newUser);
    }

    public String testMethod() {
        return authRepository.findById(1L).get().getNickname();
    }

}
