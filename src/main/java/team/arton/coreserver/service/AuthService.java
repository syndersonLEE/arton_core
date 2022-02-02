package team.arton.coreserver.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.arton.coreserver.domain.User;
import team.arton.coreserver.exception.DuplicateException;
import team.arton.coreserver.exception.NotFoundException;
import team.arton.coreserver.model.UserReqDto;
import team.arton.coreserver.repository.AuthRepository;


import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {
    private AuthRepository authRepository;

    public Optional<User> findUser(final UserReqDto userReqDto) throws NotFoundException {
        Optional<User> user = authRepository.findUserByTypeAndServerid(userReqDto.typeName(), userReqDto.getUserId());
        return user;
    }

    @Transactional
    public User saveUser(final UserReqDto userReqDto) throws DuplicateException {
        Optional<User> nicknameChecker = authRepository.findUserByNickname(userReqDto.getNickname());
        Optional<User> userIdChecker = authRepository.findUserByServerid(userReqDto.getUserId());
        if(!nicknameChecker.isEmpty() || !userIdChecker.isEmpty()) {
            return null;
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
